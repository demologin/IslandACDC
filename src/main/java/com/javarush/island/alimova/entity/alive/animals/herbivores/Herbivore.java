package com.javarush.island.alimova.entity.alive.animals.herbivores;

import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.entity.alive.animals.Animal;
import com.javarush.island.alimova.entity.alive.animals.predators.Predator;
import com.javarush.island.alimova.entity.map.Cell;

public abstract class Herbivore extends Animal {

    public Herbivore(double weight, int maxAmount, int maxSpeed, double maxFoodWeight) {
        super(weight, maxAmount, maxSpeed, maxFoodWeight);
    }


    @Override
    public void eat(Cell currentCell) {

    }

    @Override
    public void move(Cell terminalCell) {

    }

}