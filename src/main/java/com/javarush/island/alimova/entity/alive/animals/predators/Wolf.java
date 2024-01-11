package com.javarush.island.alimova.entity.alive.animals.predators;

import com.javarush.island.alimova.configure.SettingsEntity;
import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.entity.map.Cell;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class Wolf extends Predator{

    public Wolf(double weight, int maxAmount, int maxSpeed, double maxFoodWeight) {
        super(weight, maxAmount, maxSpeed, maxFoodWeight);
    }

    @Override
    public Organism clone() throws CloneNotSupportedException {
        return new Wolf(this.weight, this.maxAmount, this.maxSpeed, this.maxFoodWeight);
    }



    @Override
    public String toString() {
        return "Wolf";
    }
}
