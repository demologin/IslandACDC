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
import java.util.stream.Collectors;

public abstract class Animal extends Organisms implements Movable, Eating {

    public Animal(String name) {
        super(name);
    }

    @Override
    public boolean eat(Cell cell) {
        while (isHungry()) {

            Organisms organism = findFood(cell);
            if (organism == null || !caughtPrey(organism)) {
                return false;
            }

            double maxWeight = Limit.getMaxWeight().get(getName());
            killAndEat(cell, organism, maxWeight);
        }
        return true;
    }

    private boolean isHungry() {
        double maxWeight = Limit.getMaxWeight().get(getName());
        double normWeight = (Constants.PERCENT_NORMAL_WEIGHT * maxWeight) / 100;
        double currentWeight = getWeight();
        return currentWeight < normWeight;
    }

    private boolean caughtPrey(Organisms organisms) {
        Map<String, Map<String, Integer>> foodMap = Constants.getProbabilityBeingEaten();
        String animalName = getName();
        String preyName = organisms.getName();
        Map<String, Integer> favoriteFood = foodMap.get(animalName);
        int percentProbability = favoriteFood.get(preyName);
        return RandomNum.get(percentProbability);
    }

    private Organisms findFood(Cell cell) {
        Set<Organisms> organismsSet = cell.getOrganismsSet();

        Map<String, Map<String, Integer>> foodMap = Constants.getProbabilityBeingEaten();
        Map<String, Integer> favoriteDishes = foodMap.get(getName());

        return findWeak(organismsSet, favoriteDishes);
    }

    private Organisms findWeak(Set<Organisms> organismsSet, Map<String, Integer> favoriteFood) {
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
    }

    private void killAndEat(Cell cell, Organisms organism, double maxWeight) {
        double weightPrey = organism.getWeight();
        String nameThisAnimal = getName();
        double currentWeightThisAnimal = getWeight();
        double needFoodForFullSaturation = Limit.getNumberKgForFullSaturation().get(nameThisAnimal);
        if (weightPrey > needFoodForFullSaturation) {
            setWeight(maxWeight);
        } else {
            int hundredPercent = Constants.HUNDRED_PERCENT;
            double weightPercent = weightPrey * hundredPercent / needFoodForFullSaturation;
            double weight = weightPercent * maxWeight / hundredPercent;
            setWeight(currentWeightThisAnimal + weight);
        }
        if (getWeight() > maxWeight) {
            throw new AppException("The maximum weight of the " + this + " has been exceeded");
        }
        Set<Organisms> organismsSet = cell.getOrganismsSet();
        organismsSet.remove(organism);
    }

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
        int listAvailableCellsSize = currentCell.getAvailableCells().size();
        int indexNextCell = RandomNum.getRndNumber(0, listAvailableCellsSize);
        Cell nextCell = currentCell.getAvailableCells().get(indexNextCell);

        int numberAnimal = countNumberAnimal(nextCell.getOrganismsSet());
        int maxOfAnimalsToCell = Limit.getMaxOfAnimalsToCell().get(animalName);
        if (numberAnimal < maxOfAnimalsToCell) {
            return nextCell;
        }
        return currentCell;
    }

    private int countNumberAnimal(Set<Organisms> organismsSet) {
        Set<Organisms> thisOrganismSet = organismsSet.stream()
                .filter(o -> o.getClass().equals(this.getClass()))
                .collect(Collectors.toSet());
        return thisOrganismSet.size();
    }
}
