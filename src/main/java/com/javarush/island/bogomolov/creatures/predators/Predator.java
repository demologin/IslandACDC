package com.javarush.island.bogomolov.creatures.predators;

import com.javarush.island.bogomolov.Limit;
import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.storage.Cell;

public abstract class Predator extends Animal {
    public Predator(String name, double weight, int speedPerCell, double requiredFood, int limit) {
        super(name, weight, speedPerCell, requiredFood, limit);
    }


}
