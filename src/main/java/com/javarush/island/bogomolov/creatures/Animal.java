package com.javarush.island.bogomolov.creatures;

import com.javarush.island.bogomolov.creatures.plants.Grass;
import com.javarush.island.bogomolov.creatures.plants.Plant;
import com.javarush.island.bogomolov.creatures.predators.Bear;
import com.javarush.island.bogomolov.entity.Eating;
import com.javarush.island.bogomolov.entity.Movable;
import com.javarush.island.bogomolov.entity.Reproducible;
import com.javarush.island.bogomolov.storage.Cell;
import com.javarush.island.bogomolov.storage.IslandMap;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Organism implements Eating, Movable, Reproducible {

    private final int speedPerCell;
    private final double requiredFood;

    public Animal(String name, double weight, int speedPerCell, double requiredFood, int limit) {
        super(name, weight, limit);

        this.speedPerCell = speedPerCell;
        this.requiredFood = requiredFood;

    }

    @Override
    public boolean move(Animal animal) {
        int step;
        boolean animalMove;
        Cell[][] map=IslandMap.getislandMap().getMap();

        step = ThreadLocalRandom.current().nextInt(animal.getSpeedPerCell() + 1);
        Cell cell = IslandMap.getislandMap().getCell(animal.getRow(), animal.getColumn());
        // logic


        return false;
    }


    @Override
    public boolean eat(Object food) {
        Organism organism = null;
        double chanceToEat;
        boolean animalEat;
        if (food instanceof Organism) {
            organism = (Organism) food;


        }
        assert organism != null;
        String foodName = organism.getName();
        chanceToEat = getChancesToEat(foodName);
        animalEat = ThreadLocalRandom.current().nextDouble() < chanceToEat;
        if (animalEat) {
            Cell cell = IslandMap.getislandMap().getCell(organism.getRow(), organism.getColumn());
            if (organism instanceof Animal animal) {
                if (cell.getAnimals().contains(animal)) {
                    IslandMap.getislandMap().removeAnimal(animal, cell.getRow(), cell.getColumn());
                } else {
                    return false;
                }
            } else {
                Grass grass = (Grass) organism;
                if (!cell.getPlants().isEmpty()) {
                    IslandMap.getislandMap().removePlant(grass, cell.getRow(), cell.getColumn());
                } else {
                    return false;
                }
            }
        }


        return animalEat;
    }

    public abstract double getChancesToEat(String targetName);

    public int getSpeedPerCell() {
        return speedPerCell;
    }

    public double getRequiredFood() {
        return requiredFood;
    }
}
