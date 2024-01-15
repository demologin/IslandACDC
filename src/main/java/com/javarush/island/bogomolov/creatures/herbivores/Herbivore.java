package com.javarush.island.bogomolov.creatures.herbivores;

import com.javarush.island.bogomolov.Limit;
import com.javarush.island.bogomolov.creatures.Animal;

public abstract class Herbivore extends Animal {
    public Herbivore(String name, int weight, int speedPerCell, int requiredFood, Limit limit) {
        super(name, weight, speedPerCell, requiredFood, limit);
    }
}
