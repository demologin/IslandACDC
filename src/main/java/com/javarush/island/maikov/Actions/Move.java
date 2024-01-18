package com.javarush.island.maikov.Actions;

import com.javarush.island.maikov.Abstraction.Organism;
import com.javarush.island.maikov.Abstraction.Herbivore;
import com.javarush.island.maikov.Abstraction.Predator;
import com.javarush.island.maikov.MapOfIsland;
import com.javarush.island.maikov.methods.Statistics;

import java.util.concurrent.ThreadLocalRandom;

public class Move {
    private final Statistics statistics = new Statistics();
    public void startMove(Organism someOrganism) {

        synchronized (MapOfIsland.mapOfIsland) {
            int[] coordinates = getCoordinates(someOrganism);
            int randomSpeed = getRandomSpeed(someOrganism);
            if (randomSpeed == 0) {
                statistics.countMoving();
                return;
            }
            MapOfIsland.mapOfIsland[coordinates[0]][coordinates[1]].remove(someOrganism);
            for (int i = 0; i < randomSpeed; i++) {
                int randomMovingX = ThreadLocalRandom.current().nextInt(0, 2);
                int randomMovingY = ThreadLocalRandom.current().nextInt(0, 2);
                switch (randomMovingX) {
                    case 0 -> moveUp(coordinates);
                    case 1 -> moveDown(coordinates);
                }
                switch (randomMovingY) {
                    case 0 -> moveLeft(coordinates);
                    case 1 -> moveRight(coordinates);
                }
            }
            setNewCoordinatesAndAddToList(someOrganism, coordinates);
            minusLife(someOrganism);
            statistics.countMoving();
        }
    }
// An animal lose its life, when it moves
    private void minusLife(Organism someOrganism) {
        if(someOrganism instanceof Herbivore){
            double newLife = ((Herbivore) someOrganism).getLife();
            ((Herbivore) someOrganism).setLife(newLife - (newLife * 0.2));
        }
        if(someOrganism instanceof Predator){
            double newLife = ((Predator) someOrganism).getLife();
            ((Predator) someOrganism).setLife(newLife - (newLife * 0.2));
        }
    }

    private int getRandomSpeed(Organism someOrganism) {
        if (someOrganism instanceof Herbivore) {
            return ThreadLocalRandom.current().nextInt(0, ((Herbivore) someOrganism).getMaxSpeed() + 1);
        }
        if (someOrganism instanceof Predator) {
            return ThreadLocalRandom.current().nextInt(0, ((Predator) someOrganism).getMaxSpeed() + 1);
        } else {
            return -1;
        }
    }

    private int[] getCoordinates(Organism someOrganism) {
        int[] result = new int[2];
        if (someOrganism instanceof Herbivore) {
            result[0] = ((Herbivore) someOrganism).getX();
            result[1] = ((Herbivore) someOrganism).getY();
        }
        if (someOrganism instanceof Predator) {
            result[0] = ((Predator) someOrganism).getX();
            result[1] = ((Predator) someOrganism).getY();
        }
        return result;
    }

    private int[] moveUp(int[] coordinates) {
        if (coordinates[0] != 0) {
            coordinates[0] = coordinates[0] - 1;
            return coordinates;
        }
        coordinates[0] = MapOfIsland.mapOfIsland.length - 1;
        return coordinates;
    }

    private int[] moveDown(int[] coordinates) {
        if (coordinates[0] != MapOfIsland.mapOfIsland.length - 1) {
            coordinates[0] = coordinates[0] + 1;
            return coordinates;
        }
        coordinates[0] = 0;
        return coordinates;
    }

    private int[] moveLeft(int[] coordinates) {
        if (coordinates[1] != 0) {
            coordinates[1] = coordinates[1] - 1;
            return coordinates;
        }
        coordinates[1] = MapOfIsland.mapOfIsland[coordinates[0]].length - 1;
        return coordinates;
    }

    private int[] moveRight(int[] coordinates) {
        if (coordinates[0] != MapOfIsland.mapOfIsland.length - 1) {
            coordinates[0] = coordinates[0] + 1;
            return coordinates;
        }
        coordinates[0] = 0;
        return coordinates;
    }

    private void setNewCoordinatesAndAddToList(Organism someOrganism, int[] coordinates) {
        if (someOrganism instanceof Herbivore) {
            ((Herbivore) someOrganism).setX(coordinates[0]);
            ((Herbivore) someOrganism).setY(coordinates[1]);
            MapOfIsland.mapOfIsland[coordinates[0]][coordinates[1]].add(someOrganism);
        }
        if (someOrganism instanceof Predator) {
            ((Predator) someOrganism).setX(coordinates[0]);
            ((Predator) someOrganism).setY(coordinates[1]);
            MapOfIsland.mapOfIsland[coordinates[0]][coordinates[1]].add(someOrganism);
        }
    }

}

