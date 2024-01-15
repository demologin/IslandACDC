package com.javarush.island.bogomolov.creatures.herbivores;

import com.javarush.island.bogomolov.Limit;
import com.javarush.island.bogomolov.api.annotation.CreatureAnnotation;

@CreatureAnnotation(name="Rabbit",weight = 2,maxCountPerCell = 150,speedPerCell = 2,requiredFood = 0.45)
public class Rabbit extends Herbivore{
    public Rabbit(String name, int weight, int speedPerCell, int requiredFood, Limit limit) {
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
