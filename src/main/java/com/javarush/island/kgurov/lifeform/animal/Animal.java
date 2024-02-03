package com.javarush.island.kgurov.lifeform.animal;

import com.javarush.island.kgurov.field.Field;
import com.javarush.island.kgurov.field.GameMap;
import com.javarush.island.kgurov.lifeform.LifeForm;
import com.javarush.island.kgurov.lifeform.plant.Plant;
import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

@Getter
public abstract class Animal extends LifeForm {
    private final int step;
    private final double maxHp;
    private double hp;
    public Animal(double weight, int step, double maxHp, int maxPopulation, String name) {
        super(weight, maxPopulation, name);
        this.step = step;
        this.maxHp = maxHp;
        this.hp = maxHp;
    }
    public boolean eat(Object food) {
        if (!(food instanceof LifeForm)) {
            try {
                throw new ObjectNotLifeFormException("is not lifeform.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        LifeForm lifeForm = (LifeForm) food;
        String foodName = lifeForm.getName();
        double chanceToEat = getChanceToEat(foodName);
        boolean animalEatFood = ThreadLocalRandom
                .current()
                .nextDouble() < chanceToEat;
        if (animalEatFood) {
            setHp(Math.min((getHp() + lifeForm.getWeight()), getMaxHp()));
            Field location = GameMap
                    .getInstance()
                    .getLocation(lifeForm.getRow(), lifeForm.getCol());
            if (lifeForm instanceof Animal animal) {
                if (location.getAnimals().contains(animal)) {
                    GameMap.getInstance().removeAnimal(animal, location.getRow(), location.getCol());
                } else {
                    return false;
                }
            } else {
                Plant plant = (Plant) lifeForm;
                if (!location
                        .getPlants()
                        .isEmpty()) {
                    GameMap
                            .getInstance()
                            .removePlant(plant, location.getRow(), location.getCol());
                } else {
                    return false;
                }
            }
        }
        return animalEatFood;
    }
    public abstract double getChanceToEat(String foodName);
    public abstract void multiply(Animal partner);
    public void move() {
         int randomCells = ThreadLocalRandom
                 .current()
                 .nextInt(getStep()) + 1;
        int direction = ThreadLocalRandom
                .current()
                .nextInt(4);
        int newRow = getRow();
        int newCol = getCol();
        switch (direction) {
            case 0 -> newRow -= randomCells;
            case 1 -> newRow += randomCells;
            case 2 -> newCol -= randomCells;
            case 3 -> newCol += randomCells;
        }
        while (!isValidLocation(newRow, newCol)) {
            randomCells = ThreadLocalRandom
                    .current()
                    .nextInt(getStep()) + 1;
            direction = ThreadLocalRandom
                    .current()
                    .nextInt(4);
            newRow = getRow();
            newCol = getCol();
            switch (direction) {
                case 0 -> newRow -= randomCells;
                case 1 -> newRow += randomCells;
                case 2 -> newCol -= randomCells;
                case 3 -> newCol += randomCells;
            }
        }
        moveAnimalToNewLocation(newRow, newCol);
    }
    private boolean isValidLocation(int newRow, int newColumn) {
        return newRow >= 0 && newRow < GameMap.getInstance().getNumRows() &&
                newColumn >= 0 && newColumn < GameMap
                .getInstance().
                getNumCols();
    }
    private void moveAnimalToNewLocation(int newRow, int newCol) {
        GameMap.getInstance().removeAnimal(this, getRow(), getCol());
        setRow(newRow);
        setCol(newCol);
        GameMap.getInstance().addAnimal(this, newRow, newCol);
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

}
