package com.javarush.island.bogomolov.creatures;

import com.javarush.island.bogomolov.Limit;
import com.javarush.island.bogomolov.entity.Eating;
import com.javarush.island.bogomolov.entity.Moving;
import com.javarush.island.bogomolov.entity.Reproducible;

public abstract class Animal implements Eating, Moving, Reproducible {
    private final String name;
    private final int weight;
    private final int speedPerCell;
    private final int requiredFood;
    private final Limit limit;

    public Animal(String name, int weight, int speedPerCell, int requiredFood, Limit limit) {
        this.name = name;
        this.weight = weight;
        this.speedPerCell = speedPerCell;
        this.requiredFood = requiredFood;
        this.limit = limit;
    }


}
