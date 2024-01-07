package com.javarush.island.alimova.entity.alive.animals.predators;


import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.entity.map.Cell;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class Anaconda extends Predator {

    public Anaconda(double weight, int maxAmount, int maxSpeed, double maxFoodWeight) {
        super(weight, maxAmount, maxSpeed, maxFoodWeight);
    }

    @Override
    public Organism clone() throws CloneNotSupportedException {
        return new Anaconda(this.weight, this.maxAmount, this.maxSpeed, this.maxFoodWeight);
    }

    @Override
    public void multiply(Cell currentCell) {

    }

    @Override
    public String toString() {
        return "Anaconda{" +
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
