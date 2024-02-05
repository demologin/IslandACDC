package com.javarush.island.bogomolov.creatures.plants;

import com.javarush.island.bogomolov.creatures.Organism;
import com.javarush.island.bogomolov.entity.Reproducible;

public abstract class Plant extends Organism implements Reproducible {


    public Plant(String name, int weight, int limit) {
        super(name, weight, limit);

    }


}
