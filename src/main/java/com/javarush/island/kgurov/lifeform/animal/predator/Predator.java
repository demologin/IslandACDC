package com.javarush.island.kgurov.lifeform.animal.predator;

import com.javarush.island.kgurov.lifeform.animal.Animal;

public abstract class Predator extends Animal {
    public Predator(double weight, int maxSpeed, double maxFood, int maxAnimalCell, String name) {
        super(weight, maxSpeed, maxFood, maxAnimalCell, name);
    }
}
