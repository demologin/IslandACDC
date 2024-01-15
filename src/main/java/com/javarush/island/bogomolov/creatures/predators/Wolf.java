package com.javarush.island.bogomolov.creatures.predators;

import com.javarush.island.bogomolov.Limit;
import com.javarush.island.bogomolov.api.annotation.CreatureAnnotation;
import com.javarush.island.bogomolov.creatures.Animal;

@CreatureAnnotation(name = "Wolf", weight = 50, maxCountPerCell = 30, speedPerCell = 3, requiredFood = 8)
public class Wolf extends Predator {
    public Wolf(String name, int weight, int speedPerCell, int requiredFood, Limit limit) {
        super(name, weight, speedPerCell, requiredFood, limit);
    }

    @Override
    public boolean eat() {

        return false;
    }

    @Override
    public boolean move() {

        return false;
    }

    @Override
    public boolean spawn() {

        return false;
    }
}
