package com.javarush.island.bogomolov.creatures.predators;

import com.javarush.island.bogomolov.Limit;
import com.javarush.island.bogomolov.api.annotation.CreatureAnnotation;
import com.javarush.island.bogomolov.creatures.Animal;

@CreatureAnnotation(name="Snake",weight = 15,maxCountPerCell = 30,speedPerCell = 1,requiredFood = 3)
public class Snake extends Predator {
    public Snake(String name, int weight, int speedPerCell, int requiredFood, Limit limit) {
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
