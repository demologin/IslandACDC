package com.javarush.island.bogomolov.creatures.predators;

import com.javarush.island.bogomolov.Limit;
import com.javarush.island.bogomolov.api.annotation.CreatureAnnotation;

@CreatureAnnotation(name="Fox",weight = 8,maxCountPerCell = 30,speedPerCell = 2,requiredFood = 2)
public class Fox extends Predator{
    public Fox(String name, int weight, int speedPerCell, int requiredFood, Limit limit) {
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
