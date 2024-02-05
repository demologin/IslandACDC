package com.javarush.island.alimova.entity.alive.animals.herbivores;

import com.javarush.island.alimova.entity.alive.Organism;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class Sheep extends Herbivore{

    public Sheep(double weight, int maxAmount, int maxSpeed, double maxFoodWeight, boolean satiety) {
        super(weight, maxAmount, maxSpeed, maxFoodWeight, satiety);
    }

    @Override
    public Organism clone() throws CloneNotSupportedException {
        return new Sheep(this.weight, this.maxAmount, this.maxSpeed, this.maxFoodWeight, this.satiety);
    }

    @Override
    public String toString() {
        return "Sheep";
    }
}
