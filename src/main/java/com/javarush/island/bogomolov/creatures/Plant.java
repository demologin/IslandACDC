package com.javarush.island.bogomolov.creatures;

import com.javarush.island.bogomolov.Limit;
import com.javarush.island.bogomolov.entity.Reproducible;

public abstract class Plant extends Organism implements Reproducible {


    public Plant(String name, int weight, int limit) {
        super(name, weight, limit);

    }


}
