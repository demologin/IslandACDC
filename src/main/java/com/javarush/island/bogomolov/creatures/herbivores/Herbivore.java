package com.javarush.island.bogomolov.creatures.herbivores;

import com.javarush.island.bogomolov.creatures.Animal;

public abstract class Herbivore extends Animal {
    public Herbivore(String name, double weight, int speedPerCell, double requiredFood, int limit) {
        super(name, weight, speedPerCell, requiredFood, limit);
    }

    @Override
    public double getChancesToEat(String targetName) {
        if (targetName.equals("Grass")) {
            return 1;
        }
        return 0;
    }
}

