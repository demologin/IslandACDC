package com.javarush.island.khasanov.config;

import com.javarush.island.khasanov.entity.animals.Animal;
import com.javarush.island.khasanov.entity.plants.Plant;
import com.javarush.island.khasanov.repository.Prototype;

import java.util.HashMap;
import java.util.Map;

public class Setting {
    public static int width;
    public static int height;
    public static Map<String, Number[]> animalSettings;
    public static Map<String, Number[]> plantSettings;
    public static Map<String, Map<String, Integer>> foodSettings;
    public static Map<Prototype, String> islandObjectNames;
    public static int countPlantsForGrowing;
    private Setting(){}

    static {
        load();
    }

    private static void load() {
        loadIslandSize();
        loadAnimalSettings();
        loadPlantSettings();
        loadFoodSettings();
        loadIslandObjectNames();
    }

    public static void loadIslandObject(Animal animal) {
        Default.loadIslandObject(animal);
    }

    public static void loadIslandObject(Plant plant) {
        Default.loadIslandObject(plant);
    }

    public static void loadIslandObject(Animal animal, String className) {
        Default.loadIslandObject(animal, className);
    }

    public static void loadIslandObject(Plant plant, String className) {
        Default.loadIslandObject(plant, className);
    }

    private static void loadIslandSize() {
        width = Default.WIDTH;
        height = Default.HEIGHT;
    }

    private static void loadAnimalSettings() {
        animalSettings = Default.animalSettings;
    }

    private static void loadPlantSettings() {
        plantSettings = Default.plantSettings;
        countPlantsForGrowing = Default.COUNT_PLANTS_FOR_GROWING;
    }

    private static void loadFoodSettings() {
        foodSettings = Default.foodSettings;
    }

    private static void loadIslandObjectNames() {
        islandObjectNames = new HashMap<>();
        for (Prototype prototype : Prototype.values()) {
            String name = prototype.name();
            name = name.charAt(0) + name.substring(1).toLowerCase();
            islandObjectNames.put(prototype, name);
        }
    }


}
