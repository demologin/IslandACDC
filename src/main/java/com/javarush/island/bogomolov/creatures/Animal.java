package com.javarush.island.bogomolov.creatures;

import com.javarush.island.bogomolov.Limit;
import com.javarush.island.bogomolov.entity.Eating;
import com.javarush.island.bogomolov.entity.Movable;
import com.javarush.island.bogomolov.entity.Reproducible;
import com.javarush.island.bogomolov.storage.Cell;

public abstract class Animal extends Organism implements Eating, Movable, Reproducible {

    private final int speedPerCell;
    private final double requiredFood;

    public Animal(String name, double weight, int speedPerCell, double requiredFood, int limit) {
        super(name, weight, limit);

        this.speedPerCell = speedPerCell;
        this.requiredFood = requiredFood;

    }

    @Override
    public boolean move(Cell startcell) {
        return false;
    }

    @Override
    public boolean spawn(Cell cell) {
        return false;
    }

    @Override
    public boolean eat(Cell currentCell) {
        double chanceToEat;

        return false;
    }

    public abstract double getChancesToEat(String targetName);

    public int getSpeedPerCell() {
        return speedPerCell;
    }

    public double getRequiredFood() {
        return requiredFood;
    }
}
