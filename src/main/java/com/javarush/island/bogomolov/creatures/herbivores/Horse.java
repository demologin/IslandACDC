package com.javarush.island.bogomolov.creatures.herbivores;

import com.javarush.island.bogomolov.Limit;
import com.javarush.island.bogomolov.api.annotation.CreatureAnnotation;

@CreatureAnnotation(name="Horse",weight = 400,maxCountPerCell = 20,speedPerCell = 4,requiredFood = 60)
public class Horse extends Herbivore{
    public Horse(String name, int weight, int speedPerCell, int requiredFood, Limit limit) {
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
