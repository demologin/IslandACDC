package com.javarush.island.bogomolov.threads.animalthreads;

import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.storage.Cell;
import com.javarush.island.bogomolov.storage.IslandInitialization;
import com.javarush.island.bogomolov.storage.IslandMap;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AnimalDieOfHungerThread implements Runnable {
    private int animalDiedOfHunger;
    private final CountDownLatch countDownLatch;
    private double healthDecreasePercent = 15;

    public AnimalDieOfHungerThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        animalDiedOfHunger = 0;
        List<Animal> animals = IslandMap.getislandMap().allTheAnimals().stream().filter(animal -> animal.getRequiredFood() > 0).toList();
        if (IslandInitialization.getIslandInitialization().getCurrentTime() / 60 >= 3) {
            healthDecreasePercent = healthDecreasePercent * 2;
        }
        for (Animal animal : animals) {
            double healthDecrease = animal.getRequiredFood() * healthDecreasePercent / 100.0;
            if (animal.getHealth() - healthDecrease > 0) {
                animal.setHealth(animal.getHealth() - healthDecrease);
            } else {
                Cell cell = IslandMap.getislandMap().getCell(animal.getRow(), animal.getColumn());
                IslandMap.getislandMap().removeAnimal(animal, cell.getRow(), cell.getColumn());
                animalDiedOfHunger++;
            }

        }
        countDownLatch.countDown();

    }

    public int getAnimalDiedOfHunger() {
        return animalDiedOfHunger;
    }


}
