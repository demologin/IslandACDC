package com.javarush.island.alimova.entity.alive.animals.herbivores;

import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.entity.map.Cell;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class Mouse extends Herbivore{

    public Mouse(double weight, int maxAmount, int maxSpeed, double maxFoodWeight) {
        super(weight, maxAmount, maxSpeed, maxFoodWeight);
    }

    @Override
    public Organism clone() throws CloneNotSupportedException {
        return new Mouse(this.weight, this.maxAmount, this.maxSpeed, this.maxFoodWeight);
    }

    @Override
    public void multiply(Cell currentCell) {

    }

    @Override
    public String toString() {
        return "Mouse{" +
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
