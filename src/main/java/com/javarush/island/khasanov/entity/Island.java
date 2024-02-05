package com.javarush.island.khasanov.entity;

import com.javarush.island.khasanov.repository.IslandObjectCreator;
import com.javarush.island.khasanov.repository.Prototype;
import com.javarush.island.khasanov.util.*;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
public class Island {
    private final int WIDTH;
    private final int HEIGHT;
    private final Map<Position, Set<IslandObject>> islandMap;
    private final Map<Position, Map<Prototype, Integer>> countObjectOnField;
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
            for (Prototype prototype : Prototype.values()) {
                int count = Rndm.nextCount(prototype);
                for (int i = 0; i < count; i++) {
                    Position position = Rndm.nextPosition();
                    IslandObject islandObject = IslandObjectCreator.nextIslandObject(prototype, this, position);
                    Set<IslandObject> islandObjectsOnField = islandMap.get(position);
                    islandObjectsOnField.add(islandObject);
                    incrementObjectOnField(islandObject);
                }
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

            islandObject.saturate(food.getWeight());
            islandObject.resetReadyForReproduce();
        }
    }

    public synchronized void moveIslandObject(Position newPosition, IslandObject islandObject) {
        if (isEnoughSpace(newPosition, islandObject)) {
            Position oldPosition = islandObject.getPosition();
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

            islandObject.setIsHere(new AtomicBoolean(true));
        }
    }

    public synchronized void reproduceIslandObject(IslandObject born) {
        Position position = born.getPosition();
        Set<IslandObject> islandObjectsOnField = islandMap.get(position);
        islandObjectsOnField.add(born);
    }

    public synchronized void dieIslandObject(IslandObject islandObject) {
        Position position = islandObject.getPosition();
        Set<IslandObject> islandObjectsOnField = islandMap.get(position);
        islandObjectsOnField.remove(islandObject);
    }

    public void incrementObjectOnField(IslandObject islandObject) {
        Prototype islandObjectType = Prototype.valueOf(islandObject.getClassName().toUpperCase());
        Position position = islandObject.getPosition();
        Map<Prototype, Integer> countOnFieldMap = countObjectOnField.get(position);

        Integer countOnField = countOnFieldMap.get(islandObjectType);
        int newCountOnField = countOnField == null ? 1 : countOnField + 1;
        countOnFieldMap.put(islandObjectType, newCountOnField);
    }

    public void decrementObjectOnField(IslandObject islandObject) {
        Prototype islandObjectType = Prototype.valueOf(islandObject.getClassName().toUpperCase());
        Position position = islandObject.getPosition();
        Map<Prototype, Integer> countOnFieldMap = countObjectOnField.get(position);

        Integer countOnField = countOnFieldMap.get(islandObjectType);
        int newCountOnField = countOnField == null ? 0 : countOnField - 1;
        countOnFieldMap.put(islandObjectType, newCountOnField);
    }

    public boolean isEnoughSpace(Position position, IslandObject islandObject) {
        Map<Prototype, Integer> countMap = countObjectOnField.get(position);
        Prototype objects = Prototype.valueOf(islandObject.getClassName().toUpperCase());
        Integer count = countMap.get(objects);
        return count == null || count < islandObject.getMaxCountOnField();
    }

}
