package com.javarush.island.kgurov.simulation;

import com.javarush.island.kgurov.field.GameMap;
import com.javarush.island.kgurov.lifeform.animal.Animal;
import com.javarush.island.kgurov.lifeform.animal.herbivore.*;
import com.javarush.island.kgurov.lifeform.animal.predator.*;
import lombok.Getter;
public class Statistics implements Runnable {
    private boolean isTimeOver;
    private int cubs;
    private int animalsEaten;
    private int animalsDiedByHungry;
    private int countAnimalsEnd;
    private int countPlants;
    @Getter
    private static int currentDay = 0;
    private final AnimalMultiply animalMultiply;
    private final AnimalEat animalEat;
    private final AnimalHpDecrease animalHpDecrease;
    private int herbivoreCount;
    private int predatorCount;
    private int boarCount;
    private int buffaloCount;
    private int caterpillarCount;
    private int deerCount;
    private int duckCount;
    private int goatCount;
    private int horseCount;
    private int mouseCount;
    private int rabbitCount;
    private int sheepCount;
    private int bearCount;
    private int eagleCount;
    private int foxCount;
    private int snakeCount;
    private int wolfCount;
    public Statistics(AnimalEat animalEat, AnimalHpDecrease animalHpDecrease, AnimalMultiply animalMultiply) {
        this.animalEat = animalEat;
        this.animalHpDecrease = animalHpDecrease;
        this.animalMultiply = animalMultiply;
    }
    private int countAnimalsOfType(Class<? extends Animal> animalClass) {
        int count = 0;
        for (Animal animal : GameMap.getInstance().getAllAnimals()) {
            if (animalClass.isInstance(animal)) {
                count++;
            }
        }
        return count;
    }
    @Override
    public void run() {

        long timeNow = IslandSimulation.getInstance().getTimeNow();

        isTimeOver = checkTime(timeNow);

        cubs = animalMultiply.getCubsCount();

        herbivoreCount = countAnimalsOfType(Herbivore.class);

        boarCount = countAnimalsOfType(Boar.class);

        buffaloCount = countAnimalsOfType(Buffalo.class);

        caterpillarCount = countAnimalsOfType(Caterpillar.class);

        deerCount = countAnimalsOfType(Deer.class);

        duckCount = countAnimalsOfType(Duck.class);

        goatCount = countAnimalsOfType(Goat.class);

        horseCount = countAnimalsOfType(Horse.class);

        mouseCount = countAnimalsOfType(Mouse.class);

        rabbitCount = countAnimalsOfType(Rabbit.class);

        sheepCount = countAnimalsOfType(Sheep.class);

        predatorCount = countAnimalsOfType(Predator.class);

        bearCount = countAnimalsOfType(Bear.class);

        eagleCount = countAnimalsOfType(Eagle.class);

        foxCount = countAnimalsOfType(Fox.class);

        snakeCount = countAnimalsOfType(Snake.class);

        wolfCount = countAnimalsOfType(Wolf.class);

        countAnimalsEnd = GameMap.getInstance().getAllAnimals().size();
        animalsEaten = animalEat.getAnimalsEaten();
        animalsDiedByHungry = animalHpDecrease.getAnimalsDiedByHungry();
        countPlants = GameMap.getInstance().getAllPlants().size();
        printStats();

        if (isTimeOver) {
            IslandSimulation.getInstance().getExecutorService().shutdown();
            System.exit(0);
        }
    }
    private boolean checkTime(long timeNow) {
        return timeNow / 60 >= 3;
    }
    private void printStats() {
        if (isTimeOver) {
            System.out.println("Well played");
            System.out.println("----------------------------------");
        } else {
            System.out.printf("--- Day %d ---%n", currentDay++);
        }

        System.out.println();
        System.out.println("Island Statistics:");
        System.out.println();

        System.out.println("----------------------------------");
        printAnimalCount("Herbivore", herbivoreCount);
        System.out.println("----------------------------------");
        printAnimalCount("Boar", boarCount);
        printAnimalCount("Buffalo", buffaloCount);
        printAnimalCount("Caterpillar", caterpillarCount);
        printAnimalCount("Deer", deerCount);
        printAnimalCount("Duck", duckCount);
        printAnimalCount("Goat", goatCount);
        printAnimalCount("Horse", horseCount);
        printAnimalCount("Mouse", mouseCount);
        printAnimalCount("Rabbit", rabbitCount);
        printAnimalCount("Sheep", sheepCount);

        System.out.println("----------------------------------");
        printAnimalCount("Predator", predatorCount);
        System.out.println("----------------------------------");
        printAnimalCount("Bear", bearCount);
        printAnimalCount("Eagle", eagleCount);
        printAnimalCount("Fox", foxCount);
        printAnimalCount("Snake", snakeCount);
        printAnimalCount("Wolf", wolfCount);

        System.out.println("----------------------------------");

        System.out.printf("Animals on the island: %d%n", countAnimalsEnd);
        System.out.printf("Animals died: %d%n", animalsDiedByHungry);
        System.out.printf("Animals eaten: %d%n", animalsEaten);
        System.out.printf("Cubs born: %d%n", cubs);
        System.out.printf("Plants count: %d%n", countPlants);

        System.out.println();
        System.out.println("----------------------------------");
        System.out.println();
    }

    private void printAnimalCount(String animalType, int count) {
        System.out.printf("%s count: %d%n", animalType, count);
    }
}
