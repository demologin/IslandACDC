package com.javarush.island.maikov.Actions;

import com.javarush.island.maikov.Abstraction.Animals;
import com.javarush.island.maikov.Abstraction.Organism;
import com.javarush.island.maikov.Animals.Herbivore.Herbivore;
import com.javarush.island.maikov.Animals.Herbivore.Rabbit;
import com.javarush.island.maikov.Animals.Predators.Predator;
import com.javarush.island.maikov.Animals.Predators.Wolf;
import com.javarush.island.maikov.Grass.AbstractionGrass;
import com.javarush.island.maikov.Grass.Clover;
import com.javarush.island.maikov.MapOfIsland;
import com.javarush.island.maikov.methods.Statistics;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

// of course, I can use Reflection, but when I started making, it looks more deal for JVM, so I decided share to methods,
// a lot of codes, but this code easier for JVM.
public class Reproduce {
    public static void reproduce(Organism someOrganism) throws InterruptedException {
        synchronized (MapOfIsland.mapOfIsland) {
            if (someOrganism instanceof Herbivore) {
                try {
                    int x = ((Herbivore) someOrganism).getX();
                    int y = ((Herbivore) someOrganism).getY();
                    ArrayList<Organism> oneSpaceOfIsland = MapOfIsland.mapOfIsland[x][y];
                    for (Organism organism : oneSpaceOfIsland) {
                        if (organism.getClass().equals(someOrganism.getClass())) {
                            Rabbit newRabbit = new Rabbit(x, y);
                            MapOfIsland.mapOfIsland[x][y].add(newRabbit);
                            Statistics.addToStatistics(newRabbit);
                            Thread.sleep(1000);
                            return;
                        }
                    }
                } catch (InterruptedException e) {
                    ((Herbivore) someOrganism).getThread().join();
                }
            }
            if (someOrganism instanceof Predator) {
                try {
                    int x = ((Predator) someOrganism).getX();
                    int y = ((Predator) someOrganism).getY();
                    ArrayList<Organism> oneSpaceOfIsland = MapOfIsland.mapOfIsland[x][y];
                    for (Organism organism : oneSpaceOfIsland) {
                        if (organism.getClass().equals(someOrganism.getClass())) {
                            Wolf newWolf = new Wolf(x, y);
                            MapOfIsland.mapOfIsland[x][y].add(newWolf);
                            Statistics.addToStatistics(newWolf);
                            Thread.sleep(1000);
                            return;
                        }
                    }
                } catch (InterruptedException e) {
                    ((Predator) someOrganism).getThread().join();
                }
            }
            if (someOrganism instanceof AbstractionGrass) {
                int x = ((AbstractionGrass) someOrganism).getX();
                int y = ((AbstractionGrass) someOrganism).getY();
                int randomMoveOrStaySection = ThreadLocalRandom.current().nextInt(0, 2);
                if (randomMoveOrStaySection == 0) { // if staying in this section
                    Clover newClover = new Clover(x, y);
                    MapOfIsland.mapOfIsland[x][y].add(newClover);
                    Statistics.addToStatistics(newClover);
                    Thread.sleep(1000);
                }
                if (randomMoveOrStaySection == 1) { // moving X or Y
                    int randomMoveXOrY = ThreadLocalRandom.current().nextInt(0, 2);
                    if (randomMoveXOrY == 0) { //if moving along X
                        if (x == 0 || x == MapOfIsland.mapOfIsland.length - 1) {
                            x = x == 0 ? (x + 1) : (x - 1);
                        } else {
                            int randomMoveX = ThreadLocalRandom.current().nextInt(0, 2);
                            x = randomMoveX == 0 ? x + 1 : x - 1;
                        }
                    }
                    if (randomMoveXOrY == 1) { //if moving along Y
                        if (y == 0 || y == MapOfIsland.mapOfIsland[x].length - 1) {
                            y = y == 0 ? y + 1 : y - 1;
                        } else {
                            int randomMoveY = ThreadLocalRandom.current().nextInt(0, 2);
                            y = randomMoveY == 0 ? y + 1 : y - 1;
                        }
                    }
                    Clover newClover = new Clover(x, y);
                    MapOfIsland.mapOfIsland[x][y].add(newClover);
                    Statistics.addToStatistics(newClover);
                    Thread.sleep(1000);
                }
            }
        }
    }
}

