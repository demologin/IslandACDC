package com.javarush.island.khasanov.repository;

import com.javarush.island.khasanov.entity.*;
import com.javarush.island.khasanov.entity.animals.Animal;
import com.javarush.island.khasanov.entity.animals.herbivores.Rabbit;
import com.javarush.island.khasanov.entity.animals.predators.Wolf;
import com.javarush.island.khasanov.entity.plants.Grass;
import com.javarush.island.khasanov.util.*;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.*;

@Getter
public class Island {
    private final int WIDTH;
    private final int HEIGHT;
    private Map<Position, Set<IslandObject>> islandMap;
    @Getter(AccessLevel.PRIVATE)
    private Map<Position, Set<IslandObject>> temporaryMap;
    private final Map<Position, Map<IslandObjects, Integer>> countObjectOnField;
    private final Position[][] positions;

    public Island(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
        islandMap = new TreeMap<>();
        countObjectOnField = new HashMap<>();
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

                islandMap.put(position, islandObjects);
            }
        }
    }

    public void letAnimalsEat() {
        temporaryMap = Copy.deepCopyMap(islandMap);

        for (Map.Entry<Position, Set<IslandObject>> entry : islandMap.entrySet()) {
            for (IslandObject islandObject : entry.getValue()) {
                if (islandObject instanceof Animal animal) {
                    List<IslandObject> foodList = animal.eat();
                    eatIslandObjects(animal, foodList);
                }
            }
        }

        islandMap = temporaryMap;
    }

    private void eatIslandObjects(IslandObject animal, List<IslandObject> foodList) {
        for (IslandObject food : foodList) {
            Position position = food.getPosition();
            temporaryMap.get(position).remove(food);
            decrementObjectOnField(position, food);
            System.out.println(animal + " съел " + food);
        }
    }

    public void moveIslandObjects() {
        temporaryMap = Copy.deepCopyMap(islandMap);

        for (Map.Entry<Position, Set<IslandObject>> entry : islandMap.entrySet()) {
            for (IslandObject islandObject : entry.getValue()) {
                if (islandObject instanceof Animal animal) {
                    Position move = animal.move();
                    moveIslandObject(move, animal);
                }
            }
        }

        islandMap = temporaryMap;
    }

    private void moveIslandObject(Position newPosition, IslandObject islandObject) {
        Position oldPosition = islandObject.getPosition();
        temporaryMap.get(oldPosition).remove(islandObject);

        islandObject.setPosition(newPosition);
        Set<IslandObject> islandObjects = temporaryMap.get(newPosition);
        if (islandObjects == null) {
            islandObjects = new HashSet<>();
            islandObjects.add(islandObject);
            temporaryMap.put(newPosition, islandObjects);
        } else {
            islandObjects.add(islandObject);
        }

        decrementObjectOnField(oldPosition, islandObject);
        incrementObjectOnField(newPosition, islandObject);
    }

    public void reproduceAndGrow() {
        temporaryMap = Copy.deepCopyMap(islandMap);

        for (Map.Entry<Position, Set<IslandObject>> entry : islandMap.entrySet()) {
            for (IslandObject islandObject : entry.getValue()) {
                if (islandObject instanceof Animal animal) {
                    IslandObject born = animal.reproduce();
                    if (born != null) {
                        temporaryMap.get(entry.getKey()).add(born);
                    }
                }
            }
        }

        islandMap = temporaryMap;
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

    public void incrementObjectOnField(Position position, IslandObject islandObject) {
        IslandObjects animal = IslandObjects.valueOf(islandObject.getClassName().toUpperCase());
        Map<IslandObjects, Integer> countMap = countObjectOnField.get(position);
        Integer count = countMap.get(animal);
        count = count == null ? 1 : count+1;
        countMap.put(animal, count);
    }

    public void decrementObjectOnField(Position position, IslandObject islandObject) {
        IslandObjects animal = IslandObjects.valueOf(islandObject.getClassName().toUpperCase());
        Map<IslandObjects, Integer> countMap = countObjectOnField.get(position);
        Integer count = countMap.get(animal);
        count = count == null ? 0 : count-1;
        countMap.put(animal, count);
    }
}
