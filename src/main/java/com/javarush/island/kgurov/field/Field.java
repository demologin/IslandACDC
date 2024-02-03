package com.javarush.island.kgurov.field;

import com.javarush.island.kgurov.lifeform.LifeForm;
import com.javarush.island.kgurov.lifeform.animal.Animal;
import com.javarush.island.kgurov.lifeform.plant.Plant;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Field {
    @Getter
    private final int row;
    @Getter
    private final int col;
    private final List<Animal> animals;
    private final List<Plant> plants;
    public Field(int row, int col) {
        this.row = row;
        this.col = col;
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
    }
    private void setCoordinates(LifeForm lifeForm) {
        lifeForm.setRow(row);
        lifeForm.setCol(col);
    }
    public void addAnimal(Animal animal) {
        setCoordinates(animal);
        animals.add(animal);
    }
    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public void addPlant(Plant plant) {
        setCoordinates(plant);
        plants.add(plant);
    }
    public void removePlant(Plant plant) {
        plants.remove(plant);
    }
    public List<Plant> getPlants() {
        return Collections.unmodifiableList(plants);
    }
    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals);
    }
    public List<LifeForm> getLifeForms() {
        List<LifeForm> lifeForms = new ArrayList<>(animals);
        lifeForms.addAll(plants);
        return lifeForms;
    }

}
