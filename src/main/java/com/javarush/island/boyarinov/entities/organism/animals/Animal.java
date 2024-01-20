package com.javarush.island.boyarinov.entities.organism.animals;

import com.javarush.island.boyarinov.constants.Constants;
import com.javarush.island.boyarinov.entities.map.Cell;
import com.javarush.island.boyarinov.entities.organism.Organisms;
import com.javarush.island.boyarinov.interfaces.Eating;
import com.javarush.island.boyarinov.interfaces.Movable;
import com.javarush.island.boyarinov.interfaces.Multiplying;
import com.javarush.island.boyarinov.constants.Limit;
import com.javarush.island.boyarinov.util.RandomNum;

import java.util.Set;

public abstract class Animal extends Organisms implements Movable, Eating, Multiplying {


    @Override
    public boolean move(Cell cell) {
        String animalName = this.getClass().getSimpleName();
        int travelSpeed = Limit.getTravelSpeed().get(animalName);
        int countStep = RandomNum.getRndNumber(0, travelSpeed + 1);
        if (countStep == 0 || !isAlive(cell)) {
            return false;
        }

        Cell currentCell = cell;
        for (int i = 0; i < countStep; i++) {
            Cell nextCell = findNextCell(currentCell, animalName);
            if (nextCell == currentCell) {
                continue;
            }
            nextCell.getOrganismsSet().add(this);
            currentCell.getOrganismsSet().remove(this);
            currentCell = nextCell;
            double currentWeight = getWeight();
            double calcLoseWeight = Constants.PERCENT_LOSE_WEIGHT * currentWeight / 100;
            double newWeight = currentWeight - calcLoseWeight;
            setWeight(newWeight);
        }

        return true;
    }


    private boolean isAlive(Cell cell) {
        if (getWeight() > 0) {
            return true;
        }
        Set<Organisms> organismsSet = cell.getOrganismsSet();
        organismsSet.remove(this);
        return false;
    }

    private Cell findNextCell(Cell currentCell, String animalName) {
        int listSize = currentCell.getAvailableCells().size();
        int indexNextCell = RandomNum.getRndNumber(0, listSize);
        Cell nextCell = currentCell.getAvailableCells().get(indexNextCell);
        int numberAnimal = countNumberAnimal(nextCell.getOrganismsSet());
        int maxOfAnimalsToCell = Limit.getMaxOfAnimalsToCell().get(animalName);
        if (numberAnimal < maxOfAnimalsToCell) {
            return nextCell;
        }
        return currentCell;
    }

    private int countNumberAnimal(Set<Organisms> organismsSet) {
        int count = 0;
        for (Organisms organisms : organismsSet) {
            Class<? extends Organisms> animalClass = organisms.getClass();
            if (animalClass.equals(this.getClass())) {
                count++;
            }
        }
        return count;
    }
}
