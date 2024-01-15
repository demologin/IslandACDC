package com.javarush.island.bogomolov.creatures;

import com.javarush.island.bogomolov.Limit;
import com.javarush.island.bogomolov.entity.Reproducible;

public abstract class Plant implements Reproducible {
    private final String name;
    private final int weight;
    private final Limit limit;

    public Plant(String name, int weight, Limit limit) {
        this.name = name;
        this.weight = weight;
        this.limit = limit;
    }


}
