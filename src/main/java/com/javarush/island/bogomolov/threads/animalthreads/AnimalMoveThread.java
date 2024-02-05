package com.javarush.island.bogomolov.threads.animalthreads;

import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.storage.IslandMap;

import java.util.List;

public class AnimalMoveThread implements Runnable {

    @Override
    public void run() {
        List<Animal> animalsList = IslandMap.getislandMap().allTheAnimals().stream().filter(animal -> animal.getSpeedPerCell() > 0).toList();
        for (Animal animal : animalsList) {
            animal.move(animal);
        }


    }
}
