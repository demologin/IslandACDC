package com.javarush.island.maikov.Actions;

import com.javarush.island.maikov.Abstraction.Organism;
import com.javarush.island.maikov.Abstraction.Herbivore;
import com.javarush.island.maikov.Abstraction.Predator;
import com.javarush.island.maikov.Abstraction.Grass;
import com.javarush.island.maikov.Constants.Constants;
import com.javarush.island.maikov.Organism.Grass.Clover;
import com.javarush.island.maikov.MapOfIsland.MapOfIsland;
import com.javarush.island.maikov.methods.Statistics;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Reproduce {
    private final Statistics statistics = new Statistics();

    public void startReproduce(Organism someOrganism) throws InterruptedException {
        synchronized (MapOfIsland.mapOfIsland) {
            if (someOrganism instanceof Herbivore) {
                int x = ((Herbivore) someOrganism).getX();
                int y = ((Herbivore) someOrganism).getY();
                reproduceAnimal(someOrganism, x, y);
                statistics.countReproduce();
                return;
            }
            if (someOrganism instanceof Predator) {
                int x = ((Predator) someOrganism).getX();
                int y = ((Predator) someOrganism).getY();
                reproduceAnimal(someOrganism, x, y);
                statistics.countReproduce();
                return;
            }
            if (someOrganism instanceof Grass) {
                int x = ((Grass) someOrganism).getX();
                int y = ((Grass) someOrganism).getY();
                reproduceGrass(x, y);
                statistics.countReproduce();
            }
        }
    }

    private void reproduceGrass(int x, int y) {
        int randomMoveOrStaySection = ThreadLocalRandom.current().nextInt(0, 2);
        if (randomMoveOrStaySection == 0) { // if staying in this section
            Clover newClover = new Clover(x, y);
            MapOfIsland.mapOfIsland[x][y].add(newClover);
            statistics.addToStatistics(newClover);

        }
        if (randomMoveOrStaySection == 1) { // moving X or Y
            int randomMoveXOrY = ThreadLocalRandom.current().nextInt(0, 2);
            if (randomMoveXOrY == 0) { //if moving along X
                if (x == 0 || x == MapOfIsland.mapOfIsland.length - 1) {
                    x = x == 0 ? x + 1 : 0;
                } else {
                    int randomMoveX = ThreadLocalRandom.current().nextInt(0, 2);
                    x = randomMoveX == 0 ? x + 1 : x - 1;
                }
            }
            if (randomMoveXOrY == 1) { //if moving along Y
                if (y == 0 || y == MapOfIsland.mapOfIsland[x].length - 1) {
                    y = y == 0 ? y + 1 : 0;
                } else {
                    int randomMoveY = ThreadLocalRandom.current().nextInt(0, 2);
                    y = randomMoveY == 0 ? y + 1 : y - 1;
                }
            }
            Clover newClover = new Clover(x, y);
            MapOfIsland.mapOfIsland[x][y].add(newClover);
            statistics.addToStatistics(newClover);
        }
    }

    private void reproduceAnimal(Organism someOrganism, int x, int y) throws InterruptedException {
        ArrayList<Organism> oneSpaceOfIsland = new ArrayList<>(MapOfIsland.mapOfIsland[x][y]);
        for (Organism organism : oneSpaceOfIsland) {
            // I don't know why I can't use instanceof, maybe you know?
            if (organism.getClass().equals(someOrganism.getClass())) {
                // I made it before last lecture, so i didn't refactor it
                Class<?> aClass = organism.getClass();
                Object newAnimal;
                try {
                    Constructor<?> constructor = aClass.getConstructor(int.class, int.class);
                    try {
                        newAnimal = constructor.newInstance(x, y);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                if (someOrganism instanceof Herbivore) {
                    Thread.sleep(1000);
                    MapOfIsland.mapOfIsland[x][y].add((Herbivore) newAnimal);
                    statistics.addToStatistics((Herbivore) newAnimal);
                    minusLife(someOrganism);
                }
                if (someOrganism instanceof Predator) {
                    Thread.sleep(1000);
                    MapOfIsland.mapOfIsland[x][y].add((Predator) newAnimal);
                    statistics.addToStatistics((Predator) newAnimal);
                    minusLife(someOrganism);
                }
            }
        }
    }

    private void minusLife(Organism someOrganism) {
        if (someOrganism instanceof Herbivore) {
            double newLife = ((Herbivore) someOrganism).getLife();
            ((Herbivore) someOrganism).setLife(newLife - (newLife * Constants.MINUS_LIFE_FOR_MOVE_AND_REPRODUCE));
        }
        if (someOrganism instanceof Predator) {
            double newLife = ((Predator) someOrganism).getLife();
            ((Predator) someOrganism).setLife(newLife - (newLife * Constants.MINUS_LIFE_FOR_MOVE_AND_REPRODUCE));
        }
    }
}