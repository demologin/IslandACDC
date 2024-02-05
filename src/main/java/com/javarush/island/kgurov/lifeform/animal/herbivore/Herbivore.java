package com.javarush.island.kgurov.lifeform.animal.herbivore;

import com.javarush.island.kgurov.lifeform.animal.Animal;

public abstract class Herbivore extends Animal {
    public Herbivore(double weight, int maxSpeed, double maxFood, int maxAnimalCell, String name) {
        super(weight, maxSpeed, maxFood, maxAnimalCell, name);
    }
    @Override
    public double getChanceToEat(String foodName) {
        return (foodName.equals("Plant")) ? 1 : 0;
    }
}
