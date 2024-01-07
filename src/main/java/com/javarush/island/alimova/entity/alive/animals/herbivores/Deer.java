package com.javarush.island.alimova.entity.alive.animals.herbivores;

import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.entity.map.Cell;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class Deer extends Herbivore{

    public Deer(double weight, int maxAmount, int maxSpeed, double maxFoodWeight) {
        super(weight, maxAmount, maxSpeed, maxFoodWeight);
    }

    @Override
    public Organism clone() throws CloneNotSupportedException {
        return new Deer(this.weight, this.maxAmount, this.maxSpeed, this.maxFoodWeight);
    }

    @Override
    public void multiply(Cell currentCell) {

    }

    @Override
    public String toString() {
        return "Deer{" +
                "maxSpeed=" + maxSpeed +
                ", maxFoodWeight=" + maxFoodWeight +
                ", eatenMass=" + eatenMass +
                ", satiety=" + satiety +
                ", identification=" + identification +
                ", weight=" + weight +
                ", maxAmount=" + maxAmount +
                '}';
    }
}
