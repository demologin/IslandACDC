package com.javarush.island.kgurov.field;

import com.javarush.island.kgurov.lifeform.animal.Animal;
import com.javarush.island.kgurov.lifeform.plant.Plant;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameMap {
    private Field[][] locations;
    @Getter
    private final int numRows = 100;
    @Getter
    private final int numCols = 20; //default
    private static class Holder {
        private static final GameMap INSTANCE = new GameMap();
    }
    private GameMap() {
        locations = new Field[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                locations[i][j] = new Field(i, j);
            }
        }
    }
    public static GameMap getInstance() {
        return Holder.INSTANCE;
    }
    public Field getLocation(int row, int column) {
        return locations[row][column];
    }
    public void addAnimal(Animal animal, int row, int column) {
        getLocation(row, column).addAnimal(animal);
    }
    public void removeAnimal(Animal animal, int row, int column) {
        getLocation(row, column).removeAnimal(animal);
    }
    public void addPlant(Plant plant, int row, int column) {
        getLocation(row, column).addPlant(plant);
    }
    public void removePlant(Plant plant, int row, int column) {
        getLocation(row, column).removePlant(plant);
    }
    public List<Animal> getAllAnimals() {
        List<Animal> allAnimals = new ArrayList<>();
        for (Field[] row : locations) {
            for (Field location : row) {
                allAnimals.addAll(location.getAnimals());
            }
        }
        return Collections.unmodifiableList(allAnimals);
    }
    public List<Plant> getAllPlants() {
        List<Plant> allPlants = new ArrayList<>();
        for (Field[] row : locations) {
            for (Field location : row) {
                allPlants.addAll(location.getPlants());
            }
        }
        return Collections.unmodifiableList(allPlants);
    }

}
