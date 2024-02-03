package com.javarush.island.kgurov.simulation;

import com.javarush.island.kgurov.field.Field;
import com.javarush.island.kgurov.field.GameMap;
import com.javarush.island.kgurov.lifeform.animal.herbivore.*;
import com.javarush.island.kgurov.lifeform.animal.predator.*;
import com.javarush.island.kgurov.lifeform.plant.Plant;
import lombok.Getter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class IslandSimulation {
    private final long startTime;
    private final int countHerbivores = 200;
    private final int countPlants = 100;
    private final int countPredators = 50;
    private static volatile IslandSimulation instance;
    @Getter
    private volatile ScheduledExecutorService executorService;

    private IslandSimulation() {
        startTime = System.currentTimeMillis();
    }

    public static IslandSimulation getInstance() {
        if (instance == null) {
            synchronized (IslandSimulation.class) {
                if (instance == null) {
                    instance = new IslandSimulation();
                }
            }
        }
        return instance;
    }

    public void createIslandModel() {
        placeHerbivores(countHerbivores);
        placePredators(countPredators);
        placePlants(countPlants);

        runIslandModel();
    }

    private void runIslandModel() {
        executorService = Executors.newScheduledThreadPool(3);
        AnimalLifecycle animalLifecycle = new AnimalLifecycle();
        PlantGrowth plantGrowth = new PlantGrowth();
        Statistics statisticsTask = new Statistics(
                animalLifecycle.getAnimalEat(),
                animalLifecycle.getAnimalHpDecrease(),
                animalLifecycle.getObjectMultiplyTask()
        );
        executorService.scheduleAtFixedRate(animalLifecycle, 1, 8, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(plantGrowth, 40, 30, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(statisticsTask, 0, 8, TimeUnit.SECONDS);
    }

    private List<Herbivore> createHerbivores(int countHerbivores) {
        List<Herbivore> herbivores = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            herbivores.add(new Buffalo());
            herbivores.add(new Caterpillar());
            herbivores.add(new Deer());
            herbivores.add(new Duck());
            herbivores.add(new Goat());
            herbivores.add(new Horse());
            herbivores.add(new Mouse());
            herbivores.add(new Rabbit());
            herbivores.add(new Sheep());
            herbivores.add(new Boar());
        }
        int remainingCount = countHerbivores - herbivores.size();
        for (int i = 0; i < remainingCount; i++) {
            int randomIndex = random.nextInt(herbivores.size());
            Herbivore randomHerbivore = herbivores.get(randomIndex);
            try {
                Constructor<? extends Herbivore> constructor = randomHerbivore.getClass().getConstructor();
                Herbivore newHerbivore = constructor.newInstance();
                herbivores.add(newHerbivore);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return herbivores;
    }

    private List<Predator> createPredators(int countPredators) {
        List<Predator> predators = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            predators.add(new Bear());
            predators.add(new Eagle());
            predators.add(new Fox());
            predators.add(new Snake());
            predators.add(new Wolf());
        }
        int remainingCount = countPredators - predators.size();
        for (int i = 0; i < remainingCount; i++) {
            int randomIndex = random.nextInt(predators.size());
            Predator randomPredator = predators.get(randomIndex);
            try {
                Constructor<? extends Predator> constructor = randomPredator.getClass().getConstructor();
                Predator newPredator = constructor.newInstance();
                predators.add(newPredator);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return predators;
    }

    private List<Plant> createPlants(int countPlants) {
        List<Plant> plants = new ArrayList<>();
        for (int i = 0; i < countPlants; i++) {
            plants.add(new Plant());
        }
        return plants;
    }

    public void placeHerbivores(int countHerbivores) {
        List<Herbivore> herbivores = createHerbivores(countHerbivores);
        Random random = ThreadLocalRandom.current();
        for (Herbivore herbivore : herbivores) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(GameMap.getInstance().getNumRows());
                int column = random.nextInt(GameMap.getInstance().getNumCols());
                Field location = GameMap.getInstance().getLocation(row, column);
                if (location.getAnimals().stream().filter(c -> c.getName().equals(herbivore.getName())).toList().size() <= herbivore.getMaxPopulation()) {
                    GameMap.getInstance().addAnimal(herbivore, row, column);
                    placed = true;
                }
            }
        }
    }

    public void placePredators(int countPredators) {
        List<Predator> predators = createPredators(countPredators);
        Random random = ThreadLocalRandom.current();
        for (Predator predator : predators) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(GameMap.getInstance().getNumRows());
                int column = random.nextInt(GameMap.getInstance().getNumCols());
                Field location = GameMap.getInstance().getLocation(row, column);
                if (location.getAnimals().stream().filter(c -> c.getName().equals(predator.getName())).toList().size() <= predator.getMaxPopulation()) {
                    GameMap.getInstance().addAnimal(predator, row, column);
                    placed = true;
                }
            }
        }
    }

    public void placePlants(int countPlants) {
        List<Plant> plants = createPlants(countPlants);

        Random random = ThreadLocalRandom.current();
        for (Plant plant : plants) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(GameMap.getInstance().getNumRows());
                int column = random.nextInt(GameMap.getInstance().getNumCols());
                Field location = GameMap.getInstance().getLocation(row, column);
                if (location.getPlants().size() <= plant.getMaxPopulation()) {
                    GameMap.getInstance().addPlant(plant, row, column);
                    placed = true;
                }
            }
        }
    }

    public long getTimeNow() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

}