package com.javarush.island.alimova.entity.alive.plants;

import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.entity.map.Cell;

public abstract class Plant extends Organism {


    public Plant(double weight, int maxAmount) {
        super(weight, maxAmount);
    }


}