package com.javarush.island.bogomolov.creatures.predators;

import com.javarush.island.bogomolov.Limit;
import com.javarush.island.bogomolov.api.annotation.CreatureAnnotation;

@CreatureAnnotation(name="Bear",weight = 500,maxCountPerCell = 5,speedPerCell = 2,requiredFood = 80)
public class Bear extends Predator{
    public Bear(String name, int weight, int speedPerCell, int requiredFood, Limit limit) {
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
