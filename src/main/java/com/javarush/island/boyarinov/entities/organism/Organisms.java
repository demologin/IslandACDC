package com.javarush.island.boyarinov.entities.organism;

import com.javarush.island.boyarinov.constants.Constants;
import com.javarush.island.boyarinov.constants.Limit;
import com.javarush.island.boyarinov.entities.map.Cell;
import com.javarush.island.boyarinov.entities.organism.plants.Plant;
import com.javarush.island.boyarinov.interfaces.Eating;
import com.javarush.island.boyarinov.interfaces.Movable;
import com.javarush.island.boyarinov.interfaces.Multiplying;
import com.javarush.island.boyarinov.util.RandomNum;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Organisms implements Multiplying, Movable, Eating, Cloneable {

    private String name;
    private double weight;

    public Organisms(String name) {
        this.name = name;
    }

    @Override
    public boolean multiply(Cell cell) {
        cell.getLock().lock();
        try {
            if (!isHere(cell) || isMaxCountOrganismsTo(cell)) {
                return false;
            }
            Set<Organisms> organismsSet = cell.getOrganismsSet();
            if (this instanceof Plant) {
                List<Cell> availableCells = cell.getAvailableCells();
                int randomIndex = RandomNum.getRndNumber(0, availableCells.size());
                Cell nextCell = availableCells.get(randomIndex);
                nextCell.getLock().lock();
                try {
                    if (isMaxCountOrganismsTo(nextCell)) {
                        return false;
                    }
                    Organisms clone = this.clone();
                    nextCell.getOrganismsSet().add(clone);
                    return true;
                } finally {
                    nextCell.getLock().unlock();
                }
            }
            int numberAnimalOfThisType = countNumberOrganisms(cell, this.getClass());
            int numberAnimalForReproduction = Constants.NUMBER_ANIMAL_FOR_REPRODUCTION;
            if (numberAnimalOfThisType < numberAnimalForReproduction || !isReadyReproduce()) {
                return false;
            }
            Organisms clone = this.clone();
            double halfParentWeight = getWeight() / 2;
            clone.setWeight(halfParentWeight);
            setWeight(halfParentWeight);
            organismsSet.add(clone);
            return true;

        } finally {
            cell.getLock().unlock();
        }
    }

    @Override
    public Organisms clone() {
        try {
            Organisms clone = (Organisms) super.clone();
            double maxWeight = Limit.getMaxWeight().get(name);
            double weight = RandomNum.getRndNumber(maxWeight / 2, maxWeight);
            clone.setWeight(weight);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    protected boolean isHere(Cell currentCell) {
        currentCell.getLock().lock();
        try {
            Set<Organisms> organismsSet = currentCell.getOrganismsSet();
            return organismsSet.contains(this);
        } finally {
            currentCell.getLock().unlock();
        }
    }

    protected boolean isMaxCountOrganismsTo(Cell currentCell) {
        currentCell.getLock().lock();
        try {
            int numberAnimal = countNumberOrganisms(currentCell, this.getClass());
            int maxOfAnimalsToCell = Limit.getMaxOfAnimalsToCell().get(getName());
            return numberAnimal >= maxOfAnimalsToCell;

        } finally {
            currentCell.getLock().unlock();
        }
    }

    private boolean isReadyReproduce() {
        double maxWeight = Limit.getMaxWeight().get(getName());
        return getWeight() > maxWeight / 2;
    }

    private int countNumberOrganisms(Cell currentCell, Class<? extends Organisms> typeAnimal) {
        currentCell.getLock().lock();
        try {
            Set<Organisms> organismsSet = currentCell.getOrganismsSet();
            return organismsSet.stream()
                    .filter(o -> o.getClass().equals(typeAnimal))
                    .collect(Collectors.toSet())
                    .size();

        } finally {
            currentCell.getLock().unlock();
        }
    }

    @Override
    public boolean eat(Cell cell) {
        return false;
    }

    @Override
    public boolean move(Cell cell) {
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
