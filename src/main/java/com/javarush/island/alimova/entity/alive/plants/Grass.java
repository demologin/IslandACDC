package com.javarush.island.alimova.entity.alive.plants;

import com.javarush.island.alimova.entity.alive.Organism;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class Grass extends Plant{

    public Grass(double weight, int maxAmount) {
        super(weight, maxAmount);
    }

    @Override
    public Organism clone() throws CloneNotSupportedException {
        return new Grass(this.weight, this.maxAmount);
    }

    @Override
    public String toString() {
        return "Grass{" +
                "identification=" + identification +
                ", weight=" + weight +
                ", maxAmount=" + maxAmount +
                '}';
    }
}
