package com.javarush.island.bogomolov.storage;

import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.creatures.plants.Plant;

import java.util.ArrayList;
import java.util.List;

public class IslandMap {
    private Cell[][] map;
    private final int rows = 100;
    private final int columns = 20;
    private static volatile IslandMap islandMap;

    private IslandMap() {

    }

    public static IslandMap getislandMap() {
        if (islandMap == null) {
            synchronized (IslandMap.class) {
                if (islandMap == null) {
                    islandMap = new IslandMap();
                }
            }
        }
        return islandMap;
    }

    public void createDefaultMap() {
        map = new Cell[rows][columns];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Cell(i, j);

            }
        }
    }

    public void createMap(int rows, int columns) {
        map = new Cell[rows][columns];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Cell(i, j);
            }

        }
    }


    public Cell getCell(int row, int column) {
        return map[row][column];

    }

    public Cell[][] getMap() {
        return map;
    }

    public void addAnimal(Animal animal, int row, int column) {
        Cell cell = getCell(row, column);
        cell.addAnimal(animal);
    }

    public void addPlant(Plant plant, int row, int column) {
        Cell cell = getCell(row, column);
        cell.addPlant(plant);
    }

    public void removeAnimal(Animal animal, int row, int column) {
        Cell cell = getCell(row, column);
        cell.removeAnimal(animal);
    }

    public void removePlant(Plant plant, int row, int column) {
        Cell cell = getCell(row, column);
        cell.removePlant(plant);
    }

    public List<Animal> allTheAnimals() {
        List<Animal> animalList = new ArrayList<>();
        for (Cell[] cells : map) {
            for (Cell cell : cells) {
                animalList.addAll(cell.getAnimals());
            }
        }
        return animalList;
    }

    public List<Plant> allThePlants() {
        List<Plant> plantList = new ArrayList<>();
        for (Cell[] cells : map) {
            for (Cell cell : cells) {
                plantList.addAll(cell.getPlants());
            }
        }
        return plantList;
    }

    public  int getRows(){
        return rows;
    }

    public int getColumns() {
        return columns;
    }

}
