package com.javarush.island.bogomolov.creatures;

import com.javarush.island.bogomolov.Limit;
import com.javarush.island.bogomolov.api.annotation.CreatureAnnotation;

@CreatureAnnotation(name = "Grass", weight = 1, maxCountPerCell = 200, speedPerCell = 0, requiredFood = 0.0)
public class Grass extends Plant {
    public Grass(String name, int weight, Limit limit) {
        super(name, weight, limit);
    }

    @Override
    public boolean spawn() {
        return false;
    }
}
