package com.javarush.island.bogomolov.threads;

import com.javarush.island.bogomolov.menu.Messages;
import com.javarush.island.bogomolov.storage.IslandInitialization;
import com.javarush.island.bogomolov.storage.IslandMap;
import com.javarush.island.bogomolov.threads.animalthreads.AnimalDieOfHungerThread;
import com.javarush.island.bogomolov.threads.animalthreads.AnimalEatThread;
import com.javarush.island.bogomolov.threads.animalthreads.AnimalsSpawnThread;

public class IslandStatisticThread implements Runnable {

    private boolean isTimeOver;
    private int animalsBorn;
    private int animalsEaten;
    private int animalsDiedOfHunger;
    private int numberOfAnimalsEnd;
    private int numberOfPlants;
    private static int currentDay = 0;
    private final AnimalsSpawnThread animalsSpawnThread;
    private final AnimalEatThread animalEatThread;
    private final AnimalDieOfHungerThread animalDieOfHungerThread;


    public IslandStatisticThread(AnimalsSpawnThread animalsSpawnThread, AnimalEatThread animalEatThread, AnimalDieOfHungerThread animalDieOfHungerThread) {
        this.animalsSpawnThread = animalsSpawnThread;
        this.animalEatThread = animalEatThread;
        this.animalDieOfHungerThread = animalDieOfHungerThread;
    }


    @Override
    public void run() {
        long currentTime = IslandInitialization.getIslandInitialization().getCurrentTime();
        isTimeOver = checkTime(currentTime);

        animalsBorn = animalsSpawnThread.getAnimalsBorn();
        numberOfAnimalsEnd = IslandMap.getislandMap().allTheAnimals().size();
        animalsEaten = animalEatThread.getAnimalsEaten();
        animalsDiedOfHunger = animalDieOfHungerThread.getAnimalDiedOfHunger();
        numberOfPlants = IslandMap.getislandMap().allThePlants().size();
        printStatistics();

        if (isTimeOver) {
            IslandInitialization.getIslandInitialization().getExecutorService().shutdown();
            System.exit(0);
        }
    }


    private boolean checkTime(long currentTime) {
        return currentTime / 60 >= 5;
    }


    private void printStatistics() {
        if (isTimeOver) {
            System.out.println(Messages.WINNER);
            System.out.println(Messages.DASH);
        } else {
            System.out.printf("--- Day %d ---", currentDay);
            currentDay++;
            System.out.println();
        }
        System.out.println();
        System.out.println(Messages.ISLAND_STATISTIC);
        System.out.println();

        System.out.print(Messages.ANIMALS_ON_ISLAND);
        System.out.println(numberOfAnimalsEnd);

        System.out.print(Messages.ANIMALS_DIED_OF_HUNGER);
        System.out.println(animalsDiedOfHunger);

        System.out.print(Messages.ANIMALS_EATEN);
        System.out.println(animalsEaten);

        System.out.print(Messages.ANIMALS_BORN);
        System.out.println(animalsBorn);

        System.out.print(Messages.PLANTS_ON_ISLAND);
        System.out.println(numberOfPlants);

        System.out.println();
        System.out.println(Messages.DASH);
        System.out.println();
    }

    public static int getCurrentDay() {
        return currentDay;
    }
}

