package com.javarush.island.bogomolov.threads.animalthreads;

import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.storage.Cell;
import com.javarush.island.bogomolov.storage.IslandMap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AnimalsSpawnThread implements Runnable {
    private int animalsBorn;
    private final CountDownLatch countDownLatch;

    public AnimalsSpawnThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        animalsBorn = 0;
        List<Animal> animals = IslandMap.getislandMap().allTheAnimals();
        List<Animal> animalsSpawned = new ArrayList<>();
        if (!animals.isEmpty()) {
            for (Animal currentAnimal : animals) {
                if (!animalsSpawned.contains(currentAnimal)) {
                    Cell cell = IslandMap.getislandMap().getCell(currentAnimal.getRow(), currentAnimal.getColumn());
                    List<Animal> cellAnimals = cell.getAnimals();
                    if (cellAnimals.size() > 1) {
                        cellAnimals = cellAnimals.stream().filter(animal -> animal.getName().equals(currentAnimal.getName()) && animal != currentAnimal).toList();
                        if (!cellAnimals.isEmpty()) {
                            Animal partner = cellAnimals.get(0);
                            if (!animalsSpawned.contains(partner)) {
                                currentAnimal.spawn(partner);
                                animalsSpawned.add(currentAnimal);
                                animalsSpawned.add(partner);
                                animalsBorn++;
                            }
                        }
                    }
                }
            }
        }
        countDownLatch.countDown();

    }

    public int getAnimalsBorn() {
        return animalsBorn;
    }

}
