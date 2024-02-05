package com.javarush.island.bogomolov.storage;

import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.creatures.Organism;
import com.javarush.island.bogomolov.creatures.plants.Plant;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final int row;
    private final int column;
    private final List<Animal> animals;
    private final List<Plant> plants;


    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        animals = new ArrayList<>();
        plants = new ArrayList<>();
    }


    public void addAnimal(Animal animal) {
        animal.setRow(row);
        animal.setColumn(column);
        animals.add(animal);

    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);

    }

    public void addPlant(Plant plant) {
        plant.setRow(row);
        plant.setColumn(column);
        plants.add(plant);
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
    }


    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public List<Organism> getOrganisms() {
        List<Organism> organisms = new ArrayList<>();
        organisms.addAll(animals);
        organisms.addAll(plants);
        return organisms;
    }
}
