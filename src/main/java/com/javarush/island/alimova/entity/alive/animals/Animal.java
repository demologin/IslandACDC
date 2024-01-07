package com.javarush.island.alimova.entity.alive.animals;

import com.javarush.island.alimova.api.entity.Eating;
import com.javarush.island.alimova.api.entity.Moving;
import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.entity.map.Cell;

public abstract class Animal extends Organism implements Eating, Moving {

    protected int maxSpeed;
    protected double maxFoodWeight;
    protected double eatenMass;
    protected boolean satiety;

    public Animal(double weight, int maxAmount,
                  int maxSpeed, double maxFoodWeight) {
        super(weight, maxAmount);
        this.maxSpeed = maxSpeed;
        this.maxFoodWeight = maxFoodWeight;
        this.eatenMass = 0;
        this.satiety = false;
    }

    @Override
    public void eat(Cell currentCell) {

    }

    @Override
    public void move(Cell terminalCell) {

    }
}
