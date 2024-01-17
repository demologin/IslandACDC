package com.javarush.island.khasanov.config;

import com.javarush.island.khasanov.entity.animals.Animal;
import com.javarush.island.khasanov.entity.plants.Plant;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Default {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 50;
    public static final Map<String, Number[]> animalSettings = new HashMap<>() {{
        put("Wolf", new Number[]{50, 30, 3, 8});
        put("Rabbit", new Number[]{2, 150, 2, 0.45});
    }};

    public static final Map<String, Number[]> plantSettings = new HashMap<>() {{
        put("Grass", new Number[]{1, 200});
    }};

    public static final Map<String, Map<String, Integer>> foodSettings = new HashMap<>() {{
        put("Wolf", new TreeMap<>() {{
            put("Horse", 10);
            put("Deer", 15);
            put("Rabbit", 60);
            put("Mouse", 80);
            put("Goat", 60);
            put("Sheep", 70);
            put("WildBoar", 15);
            put("Buffalo", 10);
            put("Duck", 40);
        }});

    }};

    private Default() {
    }

    public static void loadIslandObject(Animal animal) {
        String animalName = animal.getClassName();
        loadIslandObject(animal, animalName);
    }

    public static void loadIslandObject(Plant plant) {
        String plantName = plant.getClassName();
        loadIslandObject(plant, plantName);
    }

    public static void loadIslandObject(Animal animal, String animalName) {
        Number[] animalSettings = Default.animalSettings.get(animalName);

        if (animalSettings.length == 4) {
            double weight = animalSettings[0].doubleValue();
            int maxCountOnField = animalSettings[1].intValue();
            int maxStepsPerTurn = animalSettings[2].intValue();
            double necessaryForSaturation = animalSettings[3].doubleValue();

            animal.setWeight(weight);
            animal.setMaxCountOnField(maxCountOnField);
            animal.setMaxStepsPerTurn(maxStepsPerTurn);
            animal.setNecessaryForSaturation(necessaryForSaturation);
            animal.setReadyForReproduce(true);
        }

        Map<String, Integer> food = foodSettings.get(animalName);
        animal.setFoodMap(food);
    }

    public static void loadIslandObject(Plant plant, String plantName) {
        Number[] plantSettings = Default.plantSettings.get(plantName);

        if (plantSettings.length == 2) {
            double weight = plantSettings[0].doubleValue();
            int maxCountOnField = plantSettings[1].intValue();

            plant.setWeight(weight);
            plant.setMaxCountOnField(maxCountOnField);
        }
    }
}
