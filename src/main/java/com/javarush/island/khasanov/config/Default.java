package com.javarush.island.khasanov.config;

import com.javarush.island.khasanov.entity.animals.Animal;
import com.javarush.island.khasanov.entity.plants.Plant;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Default {
    public static final int WIDTH = 3;
    public static final int HEIGHT = 5;
    public static final int COUNT_PARAMS_FOR_ANIMAL = 4;
    public static final int COUNT_PARAMS_FOR_PLANTS = 2;
    public static final int INITIAL_ANIMAL_HP = 100;
    public static final int STARVE_PER_TURN = 10;
    public static final Map<String, Number[]> animalSettings = Map.of(
            "Wolf", new Number[]{50, 30, 3, 8},
            "Rabbit", new Number[]{2, 150, 2, 0.45}
    );
    public static final Map<String, Number[]> plantSettings = Map.of(
            "Grass", new Number[]{1, 200}
    );
    public static final Map<String, Map<String, Integer>> foodSettings = Map.of(
            "Wolf", Map.of(
                    "Horse", 10,
                    "Deer", 15,
                    "Rabbit", 60,
                    "Mouse", 80,
                    "Goat", 60,
                    "Sheep", 70,
                    "WildBoar", 15,
                    "Buffalo", 10,
                    "Duck", 40
            )
    );

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

        if (animalSettings.length == COUNT_PARAMS_FOR_ANIMAL) {
            double weight = animalSettings[0].doubleValue();
            int maxCountOnField = animalSettings[1].intValue();
            int maxStepsPerTurn = animalSettings[2].intValue();
            double necessaryForSaturation = animalSettings[3].doubleValue();

            animal.setWeight(weight);
            animal.setMaxCountOnField(maxCountOnField);
            animal.setMaxStepsPerTurn(maxStepsPerTurn);
            animal.setNecessaryForSaturation(necessaryForSaturation);
            animal.setHp(new AtomicInteger(INITIAL_ANIMAL_HP));
            animal.setStarvePerTurn(STARVE_PER_TURN);
        }

        Map<String, Integer> food = foodSettings.get(animalName);
        animal.setFoodMap(food);
    }

    public static void loadIslandObject(Plant plant, String plantName) {
        Number[] plantSettings = Default.plantSettings.get(plantName);

        if (plantSettings.length == COUNT_PARAMS_FOR_PLANTS) {
            double weight = plantSettings[0].doubleValue();
            int maxCountOnField = plantSettings[1].intValue();

            plant.setWeight(weight);
            plant.setMaxCountOnField(maxCountOnField);
        }
    }
}
