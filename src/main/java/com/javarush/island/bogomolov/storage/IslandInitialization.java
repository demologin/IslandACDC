package com.javarush.island.bogomolov.storage;

import com.javarush.island.bogomolov.creatures.herbivores.*;
import com.javarush.island.bogomolov.creatures.plants.Grass;
import com.javarush.island.bogomolov.creatures.plants.Plant;
import com.javarush.island.bogomolov.creatures.predators.*;
import com.javarush.island.bogomolov.threads.IslandStatisticThread;
import com.javarush.island.bogomolov.threads.animalthreads.AnimalThreads;
import com.javarush.island.bogomolov.threads.plantthreads.PlantSpawnThread;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class IslandInitialization {
    private final int numberOfHerbivores = 400;
    private final int numberOfPredators = 200;
    private final int numberOfPlants = 500;
    private static volatile IslandInitialization islandInitialization;
    private volatile ScheduledExecutorService executorService;
    private final long startTime;

    private IslandInitialization() {
        startTime = System.currentTimeMillis();
    }

    public static IslandInitialization getIslandInitialization() {


        if (islandInitialization == null) {
            synchronized (IslandInitialization.class) {
                if (islandInitialization == null) {
                    islandInitialization = new IslandInitialization();
                }
            }
        }

        return islandInitialization;
    }

    public void createIsland(int numberOfHerbivores, int numberOfPredators, int numberOfPlants) {
        placeHerbivores(numberOfHerbivores);
        placePredators(numberOfPredators);
        placePlants(numberOfPlants);
        startIslandMap();

    }

    public void createIsland() {
        placeHerbivores(numberOfHerbivores);
        placePredators(numberOfPredators);
        placePlants(numberOfPlants);
        startIslandMap();
    }

    public void startIslandMap() {
        executorService = Executors.newScheduledThreadPool(3);
        AnimalThreads animalThreads = new AnimalThreads();
        PlantSpawnThread plantSpawnThread = new PlantSpawnThread();
        IslandStatisticThread islandStatisticThread = new IslandStatisticThread(animalThreads.getAnimalsSpawnThread(), animalThreads.getAnimalEatThread(), animalThreads.getAnimalDieOfHungerThread());
        executorService.scheduleAtFixedRate(animalThreads, 1, 8, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(plantSpawnThread, 40, 30, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(islandStatisticThread, 0, 8, TimeUnit.SECONDS);

    }

    public void placeHerbivores(int numberOfHerbivores) {
        List<Herbivore> herbivores = initialHerbivoresList(numberOfHerbivores);
        Random random = ThreadLocalRandom.current();
        for (Herbivore herbivore : herbivores) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(IslandMap.getislandMap().getRows());
                int column = random.nextInt(IslandMap.getislandMap().getColumns());
                Cell cell = IslandMap.getislandMap().getCell(row, column);
                if (cell.getAnimals().stream().filter(h -> h.getName().equals(herbivore.getName())).toList().size() <= herbivore.getLimit()) {
                    IslandMap.getislandMap().addAnimal(herbivore, row, column);
                    placed = true;

                }
            }

        }

    }

    public void placePredators(int numberOfPredators) {
        List<Predator> predators = initialPredatorsList(numberOfPredators);
        Random random = ThreadLocalRandom.current();
        for (Predator predator : predators) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(IslandMap.getislandMap().getRows());
                int column = random.nextInt(IslandMap.getislandMap().getColumns());
                Cell cell = IslandMap.getislandMap().getCell(row, column);
                if (cell.getAnimals().stream().filter(p -> p.getName().equals(predator.getName())).toList().size() <= predator.getLimit()) {
                    IslandMap.getislandMap().addAnimal(predator, row, column);
                    placed = true;

                }
            }

        }
    }

    public void placePlants(int numberOfPlants) {
        List<Plant> plants = initialPlantsList(numberOfPlants);
        Random random = ThreadLocalRandom.current();
        for (Plant plant : plants) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(IslandMap.getislandMap().getRows());
                int column = random.nextInt(IslandMap.getislandMap().getColumns());
                Cell cell = IslandMap.getislandMap().getCell(row, column);
                if (cell.getPlants().stream().filter(p -> p.getName().equals(plant.getName())).toList().size() <= plant.getLimit()) {
                    IslandMap.getislandMap().addPlant(plant, row, column);
                    placed = true;

                }
            }

        }
    }

    private List<Herbivore> initialHerbivoresList(int numberOfHerbivores) {
        List<Herbivore> initialHerbivores = new ArrayList<>();
        Random random = new Random();
        initialHerbivores.add(new Boar());
        initialHerbivores.add(new Buffalo());
        initialHerbivores.add(new Caterpillar());
        initialHerbivores.add(new Deer());
        initialHerbivores.add(new Duck());
        initialHerbivores.add(new Goat());
        initialHerbivores.add(new Horse());
        initialHerbivores.add(new Mouse());
        initialHerbivores.add(new Rabbit());
        initialHerbivores.add(new Sheep());
        int herbivoresToCreate = numberOfHerbivores - initialHerbivores.size();
        for (int i = 0; i < herbivoresToCreate; i++) {
            int randomIndex = random.nextInt(initialHerbivores.size());
            Herbivore newRandomHerbivore = initialHerbivores.get(randomIndex);
            try {
                Herbivore newHerbivore = newRandomHerbivore.getClass().getConstructor().newInstance();
                initialHerbivores.add(newHerbivore);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return initialHerbivores;
    }

    private List<Predator> initialPredatorsList(int numberOfPredators) {
        List<Predator> initialPredators = new ArrayList<>();
        Random random = new Random();
        initialPredators.add(new Bear());
        initialPredators.add(new Eagle());
        initialPredators.add(new Fox());
        initialPredators.add(new Snake());
        initialPredators.add(new Wolf());
        int predatorsToCreate = numberOfPredators - initialPredators.size();
        for (int i = 0; i < predatorsToCreate; i++) {
            int randomIndex = random.nextInt(initialPredators.size());
            Predator newRandomPredator = initialPredators.get(randomIndex);
            try {
                Predator newPredator = newRandomPredator.getClass().getConstructor().newInstance();
                initialPredators.add(newPredator);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }

        }
        return initialPredators;
    }

    private List<Plant> initialPlantsList(int numberOfPlants) {
        List<Plant> initialPlants = new ArrayList<>();
        for (int i = 0; i < numberOfPlants; i++) {
            initialPlants.add(new Grass());

        }

        return initialPlants;
    }

    public long getCurrentTime() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    public int getNumberOfHerbivores() {
        return numberOfHerbivores;
    }

    public int getNumberOfPredators() {
        return numberOfPredators;
    }

    public int getNumberOfPlants() {
        return numberOfPlants;
    }

    public ScheduledExecutorService getExecutorService() {
        return executorService;
    }
}
