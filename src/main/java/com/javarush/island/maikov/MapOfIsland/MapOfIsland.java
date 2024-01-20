package com.javarush.island.maikov.MapOfIsland;

import com.javarush.island.maikov.Abstraction.Organism;
import com.javarush.island.maikov.Constants.Constants;
import com.javarush.island.maikov.Organism.Grass.Clover;
import com.javarush.island.maikov.Organism.Herbivore.*;
import com.javarush.island.maikov.Organism.Predators.*;
import com.javarush.island.maikov.methods.PrintToConsole;
import com.javarush.island.maikov.methods.Statistics;

import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class MapOfIsland {
    public static final HashSet<Organism>[][] mapOfIsland = new HashSet[10][10];
    private static boolean gameWorker = true;

    public MapOfIsland() {
        ReentrantLock reentrantLock = new ReentrantLock();
        try {
            reentrantLock.lock();
            createOrganismsAtStart();
        } finally {
            reentrantLock.unlock();
        }
        System.out.println("Loading animals map at the start.Please wait.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Возникла проблема при загрузке");
        }
        PrintToConsole print = new PrintToConsole();


        while (gameWorker) {
            if (Statistics.countHerbivores.get() == 0) {
                System.out.println("No more Herbivores, the game is stopping");
                gameWorker = false;
            }
            if (Statistics.countPredators.get() == 0) {
                System.out.println("No more Predators, the game is stopping");
                gameWorker = false;
            }
            print.printToConsole(mapOfIsland);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void createOrganismsAtStart() {
        Statistics statistics = new Statistics();
        for (int i = 0; i < mapOfIsland.length; i++) {
            for (int j = 0; j < mapOfIsland[i].length; j++) {
                byte countOfPredators = (byte) ThreadLocalRandom.current().nextInt(Constants.minValueOfAnimalsOnOneCell,
                        Constants.maxValueOfAnimalsOnOneCell);
                byte countOfHerbivores = (byte) ThreadLocalRandom.current().nextInt(Constants.minValueOfAnimalsOnOneCell,
                        Constants.maxValueOfAnimalsOnOneCell);
                byte countOfClover = (byte) ThreadLocalRandom.current().nextInt(Constants.minValueOfGrassOnOneCell,
                        Constants.maxValueOfGrassOnOneCell);
                mapOfIsland[i][j] = new HashSet<>();
                for (int herb = 0; herb < countOfHerbivores; herb++) {
                    randomChoseHerbivore(i, j, statistics);
                }
                for (int pred = 0; pred < countOfPredators; pred++) {
                    randomChosePredators(i, j, statistics);
                }
                for (int grass = 0; grass < countOfClover; grass++) {
                    Clover newClover = new Clover(i, j);
                    mapOfIsland[i][j].add(newClover);
                    statistics.addToStatistics(newClover);
                }
            }
        }
    }

    private void randomChosePredators(int i, int j, Statistics statistics) {
        byte randomChose = (byte) ThreadLocalRandom.current().nextInt(Constants.minRandomPredatorsOnOneCell,
                Constants.maxRandomPredatorsOnOneCell + 1);
        switch (randomChose) {
            case 1 -> {
                Bear newBear = new Bear(i, j);
                mapOfIsland[i][j].add(newBear);
                statistics.addToStatistics(newBear);
            }
            case 2 -> {
                Boa newBoa = new Boa(i, j);
                mapOfIsland[i][j].add(newBoa);
                statistics.addToStatistics(newBoa);
            }
            case 3 -> {
                Eagle newEagle = new Eagle(i, j);
                mapOfIsland[i][j].add(newEagle);
                statistics.addToStatistics(newEagle);
            }
            case 4 -> {
                Fox newFox = new Fox(i, j);
                mapOfIsland[i][j].add(newFox);
                statistics.addToStatistics(newFox);
            }
            case 5 -> {
                Wolf newWolf = new Wolf(i, j);
                mapOfIsland[i][j].add(newWolf);
                statistics.addToStatistics(newWolf);
            }

        }
    }

    private void randomChoseHerbivore(int i, int j, Statistics statistics) {
        byte randomChose = (byte) ThreadLocalRandom.current().nextInt(Constants.minRandomHerbivoresOnOneCell,
                Constants.maxRandomHerbivoresOnOneCell + 1);
        switch (randomChose) {
            case 1 -> {
                Boar newBoar = new Boar(i, j);
                mapOfIsland[i][j].add(newBoar);
                statistics.addToStatistics(newBoar);
            }
            case 2 -> {
                Buffalo newBuffalo = new Buffalo(i, j);
                mapOfIsland[i][j].add(newBuffalo);
                statistics.addToStatistics(newBuffalo);
            }
            case 3 -> {
                Caterpillar newCaterpillar = new Caterpillar(i, j);
                mapOfIsland[i][j].add(newCaterpillar);
                statistics.addToStatistics(newCaterpillar);
            }
            case 4 -> {
                Deer newDeer = new Deer(i, j);
                mapOfIsland[i][j].add(newDeer);
                statistics.addToStatistics(newDeer);
            }
            case 5 -> {
                Duck newDuck = new Duck(i, j);
                mapOfIsland[i][j].add(newDuck);
                statistics.addToStatistics(newDuck);
            }
            case 6 -> {
                Goat newGoat = new Goat(i, j);
                mapOfIsland[i][j].add(newGoat);
                statistics.addToStatistics(newGoat);
            }
            case 7 -> {
                Horse newHorse = new Horse(i, j);
                mapOfIsland[i][j].add(newHorse);
                statistics.addToStatistics(newHorse);
            }
            case 8 -> {
                Mouse newMouse = new Mouse(i, j);
                mapOfIsland[i][j].add(newMouse);
                statistics.addToStatistics(newMouse);
            }
            case 9 -> {
                Rabbit newRabbit = new Rabbit(i, j);
                mapOfIsland[i][j].add(newRabbit);
                statistics.addToStatistics(newRabbit);
            }
            case 10 -> {
                Sheep newSheep = new Sheep(i, j);
                mapOfIsland[i][j].add(newSheep);
                statistics.addToStatistics(newSheep);
            }
        }
    }
}
