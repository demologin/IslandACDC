package com.javarush.island.boyarinov.entities.organism.animals;

import com.javarush.island.boyarinov.entities.map.Cell;
import com.javarush.island.boyarinov.entities.organism.Organisms;
import com.javarush.island.boyarinov.interfaces.Eating;
import com.javarush.island.boyarinov.interfaces.Movable;
import com.javarush.island.boyarinov.interfaces.Multiplying;
import com.javarush.island.boyarinov.util.Limit;
import com.javarush.island.boyarinov.util.RandomNum;

import java.util.Set;

public abstract class Animal extends Organisms implements Movable, Eating, Multiplying {


    @Override
    public boolean move(Cell cell) {
        int travelSpeed = Limit.getTravelSpeed().get(this.getClass());
        int countStep = RandomNum.getRndNumber(0, travelSpeed + 1);
        if (countStep == 0) {
            return false;
        }

        Cell currentCell = cell;
        for (int i = 0; i < countStep; i++) {
            Cell nextCell = findNextCell(currentCell);
            if (nextCell == null) {
                continue;
            }
            nextCell.getOrganismsSet().add(this);
            currentCell.getOrganismsSet().remove(this);
            currentCell = nextCell;
            double currentWeight = getWeight();
            double newWeight = currentWeight - (10 * currentWeight / 100);
            setWeight(newWeight);
        }

        return true;
    }

    private Cell findNextCell(Cell cell) {
        int size = cell.getAvailableCells().size();
        int index = RandomNum.getRndNumber(0, size);
        Cell nextCell = cell.getAvailableCells().get(index);
        int numberAnimal = countNumberAnimal(nextCell.getOrganismsSet());
        int maxOfAnimalsToCell = Limit.getMaxOfAnimalsToCell().get(this.getClass());
        if (numberAnimal < maxOfAnimalsToCell) {
            return nextCell;
        }
        return null;
    }

    private int countNumberAnimal(Set<Organisms> organismsSet) {
        int count = 0;
        for (Organisms organisms : organismsSet) {
            Class<? extends Organisms> aClass = organisms.getClass();
            if (aClass.equals(this.getClass())) {
                count++;
            }
        }
        return count;
    }
}
