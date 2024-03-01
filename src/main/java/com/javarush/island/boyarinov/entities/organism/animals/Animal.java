package com.javarush.island.boyarinov.entities.organism.animals;

import com.javarush.island.boyarinov.constants.Constants;
import com.javarush.island.boyarinov.constants.Limit;
import com.javarush.island.boyarinov.entities.map.Cell;
import com.javarush.island.boyarinov.entities.organism.Organisms;
import com.javarush.island.boyarinov.exceptions.AppException;
import com.javarush.island.boyarinov.interfaces.Eating;
import com.javarush.island.boyarinov.interfaces.Movable;
import com.javarush.island.boyarinov.util.RandomNum;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Animal extends Organisms implements Movable, Eating {

    public Animal(String name) {
        super(name);
    }

    @Override
    public boolean eat(Cell cell) {
        if (!isHungry(cell) || !isHere(cell)) {
            return false;
        }
        while (isHungry(cell) && isHere(cell)) {

            Organisms prey = findFood(cell);
            if (prey == null || !caughtPrey(prey)) {
                return false;
            }


            killAndEat(cell, prey);
        }
        return true;

    }

    private boolean isHungry(Cell cell) {
        cell.getLock().lock();
        try {
            double maxWeight = Limit.getMaxWeight().get(getName());
            double hundredPercent = Constants.HUNDRED_PERCENT;
            double normWeight = (Constants.PERCENT_NORMAL_WEIGHT * maxWeight) / hundredPercent;
            double currentWeight = getWeight();
            return currentWeight < normWeight;

        } finally {
            cell.getLock().unlock();
        }
    }

    private boolean caughtPrey(Organisms prey) {
        Map<String, Map<String, Integer>> foodMap = Constants.getProbabilityBeingEaten();
        String animalName = getName();
        String preyName = prey.getName();
        Map<String, Integer> favoriteFood = foodMap.get(animalName);
        int percentProbability = favoriteFood.get(preyName);
        return RandomNum.get(percentProbability);
    }

    private Organisms findFood(Cell cell) {
        Map<String, Map<String, Integer>> foodMap = Constants.getProbabilityBeingEaten();
        Map<String, Integer> favoriteDishes = foodMap.get(getName());

        return findWeak(cell, favoriteDishes);
    }

    private Organisms findWeak(Cell cell, Map<String, Integer> favoriteFood) {
        cell.getLock().lock();
        try {
            Set<Organisms> organismsSet = cell.getOrganismsSet();
            Map<Organisms, Integer> tempMap = new HashMap<>();
            for (Organisms organism : organismsSet) {
                String organismName = organism.getName();
                if (favoriteFood.containsKey(organismName)) {
                    int percent = favoriteFood.get(organismName);
                    tempMap.put(organism, percent);
                }
            }
            Map.Entry<Organisms, Integer> organismEntry = tempMap.entrySet().stream()
                    .max(Comparator.comparingInt(Map.Entry::getValue))
                    .orElse(null);
            return organismEntry == null ? null : organismEntry.getKey();

        } finally {
            cell.getLock().unlock();
        }
    }

    private void killAndEat(Cell cell, Organisms prey) {
        cell.getLock().lock();
        try {
            double maxWeight = Limit.getMaxWeight().get(getName());
            double hundredPercent = Constants.HUNDRED_PERCENT;
            double weightPrey = prey.getWeight();
            double currentWeightThisAnimal = getWeight();
            double needFoodForFullSaturation = Limit.getNumberKgForFullSaturation().get(getName());
            double percentageUnderweight = hundredPercent - (currentWeightThisAnimal * hundredPercent / maxWeight);
            double needFood = percentageUnderweight * needFoodForFullSaturation / hundredPercent;
            if (weightPrey > needFood) {
                setWeight(maxWeight);
            } else {
                double weightPreyInPercent = weightPrey * hundredPercent / needFood;
                double underweight = maxWeight - currentWeightThisAnimal;
                double weight = weightPreyInPercent * underweight / hundredPercent;
                setWeight(currentWeightThisAnimal + weight);
            }
            if (getWeight() > maxWeight) {
                throw new AppException("The maximum weight of the " + this + " has been exceeded");
            }
            Set<Organisms> organismsSet = cell.getOrganismsSet();
            organismsSet.remove(prey);

        } finally {
            cell.getLock().unlock();
        }
    }

    @Override
    public boolean move(Cell cell) {
        if (!isAlive(cell) || !isHere(cell)) {
            return false;
        }
        Map<String, Integer> travelSpeedMap = Limit.getTravelSpeed();
        int travelSpeed = travelSpeedMap.get(getName());
        int countStep = RandomNum.getRndNumber(0, travelSpeed + 1);

        Cell nextCell = findNextCell(cell, countStep);
        goTo(nextCell, cell);

        return true;
    }

    private boolean isAlive(Cell cell) {
        cell.getLock().lock();
        try {
            if (getWeight() > 0.000) {
                return true;
            }
            Set<Organisms> organismsSet = cell.getOrganismsSet();
            organismsSet.remove(this);
            return false;

        } finally {
            cell.getLock().unlock();
        }
    }

    private Cell findNextCell(Cell currentCell, int countStep) {
        Cell nextCell = currentCell;
        for (int i = 0; i < countStep; i++) {
            int listAvailableCellsSize = nextCell.getAvailableCells().size();
            int indexNextCell = RandomNum.getRndNumber(0, listAvailableCellsSize);
            nextCell = nextCell.getAvailableCells().get(indexNextCell);
        }
        if (isMaxCountOrganismsTo(nextCell)) {
            return currentCell;
        }
        return nextCell;

    }

    private void goTo(Cell nextCell, Cell currentCell) {
        nextCell.getLock().lock();
        try {
            nextCell.getOrganismsSet().add(this);
            currentCell.getLock().lock();
            try {
                currentCell.getOrganismsSet().remove(this);
            } finally {
                currentCell.getLock().unlock();
            }
            double currentWeight = getWeight();
            double hundredPercent = Constants.HUNDRED_PERCENT;
            double calcLoseWeight = Constants.PERCENT_LOSE_WEIGHT * currentWeight / hundredPercent;
            double newWeight = currentWeight - calcLoseWeight;
            setWeight(newWeight);

        } finally {
            nextCell.getLock().unlock();
        }
    }
}
