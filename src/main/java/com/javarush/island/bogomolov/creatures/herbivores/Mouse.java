package com.javarush.island.bogomolov.creatures.herbivores;

import com.javarush.island.bogomolov.Limit;
import com.javarush.island.bogomolov.api.annotation.CreatureAnnotation;

@CreatureAnnotation(name="Mouse",weight = 0.05,maxCountPerCell = 500,speedPerCell = 1,requiredFood = 0.01)
public class Mouse extends Herbivore{
    public Mouse(String name, int weight, int speedPerCell, int requiredFood, Limit limit) {
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
