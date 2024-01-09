package com.javarush.island.alimova.entity.alive.animals;

import com.javarush.island.alimova.api.entity.Eating;
import com.javarush.island.alimova.api.entity.Moving;
import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.entity.map.Cell;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Animal animal = (Animal) o;
        return maxSpeed == animal.maxSpeed && Double.compare(maxFoodWeight, animal.maxFoodWeight) == 0 && Double.compare(eatenMass, animal.eatenMass) == 0 && satiety == animal.satiety;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxSpeed, maxFoodWeight, eatenMass, satiety);
    }

    @Override
    public void eat(Cell currentCell) {

    }

    @Override
    public void move(Cell terminalCell) {

    }
}
