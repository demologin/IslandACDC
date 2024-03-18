package com.javarush.island.boyarinov.entities.organism;

import com.javarush.island.boyarinov.constants.Limit;
import com.javarush.island.boyarinov.entities.map.Cell;
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
        if (!isHere(cell) || isMaxCountOrganismsTo(cell)) {
            return false;
        }
        List<Cell> availableCells = cell.getAvailableCells();
        int randomIndex = RandomNum.getRndNumber(0, availableCells.size());
        Cell nextCell = availableCells.get(randomIndex);
        if (isMaxCountOrganismsTo(nextCell)) {
            return false;
        }
        Organisms clone = this.clone();
        return safeAddTo(nextCell, clone);
    }

    protected boolean safeAddTo(Cell cell, Organisms organism) {
        cell.getLock().lock();
        try {
            Set<Organisms> organismsSet = cell.getOrganismsSet();
            return organismsSet.add(organism);
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
        int numberAnimal = countNumberOrganisms(currentCell, this.getClass());
        int maxOfAnimalsToCell = Limit.getMaxOfAnimalsToCell().get(getName());
        return numberAnimal >= maxOfAnimalsToCell;
    }

    protected boolean isReadyReproduce() {
        double maxWeight = Limit.getMaxWeight().get(getName());
        return getWeight() > maxWeight / 2;
    }

    protected int countNumberOrganisms(Cell currentCell, Class<? extends Organisms> typeAnimal) {
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
