package com.javarush.island.khasanov.entity;

import com.javarush.island.khasanov.entity.animals.herbivores.*;
import com.javarush.island.khasanov.entity.animals.predators.*;
import com.javarush.island.khasanov.entity.plants.*;
import com.javarush.island.khasanov.repository.Position;
import com.javarush.island.khasanov.repository.Prototypes;
import com.javarush.island.khasanov.util.*;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
public class Island {
    private final int WIDTH;
    private final int HEIGHT;
    private final Map<Position, Set<IslandObject>> islandMap;
    private final Map<Position, Map<Prototypes, Integer>> countObjectOnField;
    private final Position[][] positions;

    public Island(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
        islandMap = new TreeMap<>();
        countObjectOnField = new TreeMap<>();
        positions = new Position[WIDTH][HEIGHT];
    }

    public void fill() {
        if (islandMap.isEmpty()) {
            createEmptyFields();
            for (int i = 0; i < 3; i++) {
                Set<IslandObject> islandObjects = new HashSet<>();
                Position position = Rndm.nextPosition();
                islandObjects.add(new Rabbit(this, position));
                islandObjects.add(new Rabbit(this, position));
                islandObjects.add(new Grass(this, position));
                islandObjects.add(new Grass(this, position));
                islandObjects.add(new Wolf(this, position));
                islandObjects.forEach(this::incrementObjectOnField);
                islandMap.put(position, islandObjects);
            }
        }
    }

    private void createEmptyFields() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                Position position = new Position(x, y);
                positions[x][y] = position;
                islandMap.put(position, new HashSet<>());
                countObjectOnField.put(position, new TreeMap<>());
            }
        }
    }

    public synchronized void eatFromFoodList(IslandObject islandObject, List<IslandObject> foodList) {
        for (IslandObject food : foodList) {
            Position position = food.getPosition();
            Set<IslandObject> islandObjectsOnField = islandMap.get(position);
            islandObjectsOnField.remove(food);

            decrementObjectOnField(food);
            islandObject.saturate(food.getWeight());
            islandObject.resetReadyForReproduce();

            System.out.println(islandObject + " съел " + food);
        }
    }

    public synchronized void moveIslandObject(Position newPosition, IslandObject islandObject) {
        if (isEnoughSpace(newPosition, islandObject)) {
            Position oldPosition = islandObject.getPosition();
            decrementObjectOnField(islandObject);
            Set<IslandObject> islandObjectsOnOldField = islandMap.get(oldPosition);
            islandObjectsOnOldField.remove(islandObject);

            islandObject.setPosition(newPosition);
            Set<IslandObject> islandObjectsOnNewField = islandMap.get(newPosition);
            if (islandObjectsOnNewField == null) {
                islandObjectsOnNewField = new HashSet<>();
                islandObjectsOnNewField.add(islandObject);
                islandMap.put(newPosition, islandObjectsOnNewField);
            } else {
                islandObjectsOnNewField.add(islandObject);
            }

            incrementObjectOnField(islandObject);
        }
        islandObject.setIsHere(new AtomicBoolean(true));
    }

    public synchronized void reproduceIslandObject(IslandObject born) {
        Position position = born.getPosition();
        Set<IslandObject> islandObjectsOnField = islandMap.get(position);
        islandObjectsOnField.add(born);
        incrementObjectOnField(born);
    }

    public synchronized void dieIslandObject(IslandObject islandObject) {
        Position position = islandObject.getPosition();
        Set<IslandObject> islandObjectsOnField = islandMap.get(position);
        islandObjectsOnField.remove(islandObject);
        decrementObjectOnField(islandObject);
    }

    public void incrementObjectOnField(IslandObject islandObject) {
        Prototypes islandObjectType = Prototypes.valueOf(islandObject.getClassName().toUpperCase());
        Position position = islandObject.getPosition();
        Map<Prototypes, Integer> countOnFieldMap = countObjectOnField.get(position);

        Integer countOnField = countOnFieldMap.get(islandObjectType);
        int newCountOnField = countOnField == null ? 1 : countOnField + 1;
        countOnFieldMap.put(islandObjectType, newCountOnField);
    }

    public void decrementObjectOnField(IslandObject islandObject) {
        Prototypes islandObjectType = Prototypes.valueOf(islandObject.getClassName().toUpperCase());
        Position position = islandObject.getPosition();
        Map<Prototypes, Integer> countOnFieldMap = countObjectOnField.get(position);

        Integer countOnField = countOnFieldMap.get(islandObjectType);
        int newCountOnField = countOnField == null ? 0 : countOnField - 1;
        countOnFieldMap.put(islandObjectType, newCountOnField);
    }

    private boolean isEnoughSpace(Position position, IslandObject islandObject) {
        Map<Prototypes, Integer> countMap = countObjectOnField.get(position);
        Prototypes objects = Prototypes.valueOf(islandObject.getClassName().toUpperCase());
        Integer count = countMap.get(objects);
        return count == null || count < islandObject.getMaxCountOnField();
    }

}
