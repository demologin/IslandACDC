package com.javarush.island.kgurov.simulation;

import com.javarush.island.kgurov.field.GameMap;
import com.javarush.island.kgurov.lifeform.animal.Animal;

import java.util.List;

public class AnimalMove implements Runnable {
    @Override
    public void run() {
        List<Animal> animals = GameMap.getInstance().getAllAnimals().stream().filter(c -> c.getStep() > 0).toList();
        for (Animal animal : animals) {
            animal.move();
        }
    }
}
