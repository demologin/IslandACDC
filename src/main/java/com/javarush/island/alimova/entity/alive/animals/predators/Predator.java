package com.javarush.island.alimova.entity.alive.animals.predators;

import com.javarush.island.alimova.entity.alive.animals.Animal;

public abstract class Predator extends Animal {


    public Predator(double weight, int maxAmount, int maxSpeed, double maxFoodWeight, boolean satiety) {
        super(weight, maxAmount, maxSpeed, maxFoodWeight, satiety);
    }
}
