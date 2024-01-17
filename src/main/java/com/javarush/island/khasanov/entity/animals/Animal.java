package com.javarush.island.khasanov.entity.animals;

import com.javarush.island.khasanov.config.Setting;
import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.repository.*;
import com.javarush.island.khasanov.util.Rndm;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Setter
@Getter
public class Animal extends IslandObject {
    private int maxStepsPerTurn;
    private double necessaryForSaturation;
    private Map<String, Integer> foodMap;
    private boolean readyForReproduce;

    protected Animal(Island island, Position position) {
        super(island, position);
        Setting.loadIslandObject(this);
    }

    protected Animal() {
        super();
    }

    protected Animal(Animal animal) {
        super(animal.island, animal.position);
        maxStepsPerTurn = animal.getMaxStepsPerTurn();
        necessaryForSaturation = animal.getNecessaryForSaturation();
        readyForReproduce = true;
        foodMap = new HashMap<>(animal.getFoodMap());
    }


    public List<IslandObject> eat() {
        List<IslandObject> foodList = new ArrayList<>();
        Set<IslandObject> nearbyObjects = island.getIslandMap().get(position);

        for (IslandObject object : nearbyObjects) {
            String foodName = object.getClassName();
            Integer probability = foodMap.get(foodName);

            if (object.isAlive() && probability != null) {
                int random = ThreadLocalRandom.current().nextInt(1,100);
                if (probability >= random) {
                    foodList.add(object);
                    object.die();
                }
            }
        }

        return foodList;
    }

    public Position move() {
        int limitX = Setting.width - 1;
        int limitY = Setting.height - 1;

        int x = Rndm.nextSteps(maxStepsPerTurn, limitX);
        int y = Rndm.nextSteps(maxStepsPerTurn-x, limitY);

        int forwardX = adjustStep(position.getX() + x, limitX);
        int forwardY = adjustStep(position.getY() + y, limitY);
        int backX = adjustStep(position.getX() - x, limitX);
        int backY = adjustStep(position.getY() - y, limitY);

        x = Rndm.choose(forwardX, backX);
        y = Rndm.choose(forwardY, backY);

        Position newPosition = island.getPositions()[x][y];
        resetReadyForReproduce();
        return newPosition;
    }

    private int adjustStep(int step, int limit) {
        int result = Math.min(step, limit);

        if (step < 0) {
            result = 0;
        }

        return result;
    }

    public IslandObject reproduce() {
        if (!readyForReproduce) {
            return null;
        }

        Set<IslandObject> nearbyAnimals = new HashSet<>(island.getIslandMap().get(position));
        nearbyAnimals.remove(this);

        for (IslandObject animal : nearbyAnimals) {
            if (Objects.equals(animal.getClassName(), this.getClassName())) {
                IslandObject prepared = IslandObjects.get(getClassName());
                ((Animal) animal).setReadyForReproduce(false);
                setReadyForReproduce(false);
                return prepared.copyOf(animal);
            }
        }

        return null;
    }

    protected void addFood(Map<String, Integer> food) {
        if (this.foodMap == null) {
            setFoodMap(food);
        } else {
            this.foodMap.putAll(food);
        }
    }

    public IslandObject copyOf(IslandObject object) {
        Animal copy = new Animal((Animal) object);
        Setting.loadIslandObject(copy, getClassName());
        return copy;
    }

    private void resetReadyForReproduce() {
        setReadyForReproduce(true);
    }
}
