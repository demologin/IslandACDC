package com.javarush.island.bogomolov.storage;

import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.creatures.Plant;
import com.javarush.island.bogomolov.creatures.herbivores.Herbivore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IslandMap {
    private final Cell[][] map;
    private final int rows = 100;
    private final int columns = 20;


    public IslandMap() {
        this.map = new Cell[this.rows][this.columns];
    }

    private IslandMap(int rows, int columns) {
        this.map = new Cell[rows][columns];
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


}
