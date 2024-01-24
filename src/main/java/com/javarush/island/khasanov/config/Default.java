package com.javarush.island.khasanov.config;

import com.javarush.island.khasanov.entity.animals.Animal;
import com.javarush.island.khasanov.entity.plants.Plant;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Default {
    public static final int WIDTH = 100;
    public static final int HEIGHT = 20;
    public static final int COUNT_PARAMS_FOR_ANIMAL = 4;
    public static final int COUNT_PARAMS_FOR_PLANTS = 2;
    public static final int INITIAL_ANIMAL_HP = 100;
    public static final int STARVE_PER_TURN = 10;
    public static final int COUNT_PLANTS_FOR_GROWING = 50;
    public static final Map<String, Number[]> animalSettings = new HashMap<>() {{
            put("Wolf", new Number[]{50, 30, 3, 8});
            put("Boa", new Number[]{15, 30, 3, 8});
            put("Fox", new Number[]{8, 30, 2, 2});
            put("Bear", new Number[]{500, 5, 2, 80});
            put("Eagle", new Number[]{6, 20, 3, 1});
            put("Horse", new Number[]{400, 20, 4, 60});
            put("Deer", new Number[]{300, 20, 4, 50});
            put("Rabbit", new Number[]{2, 150, 2, 0.45});
            put("Mouse", new Number[]{0.05, 500, 1, 0.01});
            put("Goat", new Number[]{60, 140, 3, 10});
            put("Sheep", new Number[]{70, 140, 3, 15});
            put("Hog", new Number[]{400, 50, 2, 50});
            put("Buffalo", new Number[]{700, 10, 3, 100});
            put("Duck", new Number[]{1, 200, 4, 0.15});
            put("Caterpillar", new Number[]{0.01, 1000, 0, 0});
    }};
    public static final Map<String, Number[]> plantSettings = Map.of(
            "Grass", new Number[]{1, 200}
    );
    public static final Map<String, Map<String, Integer>> foodSettings = new HashMap<>() {{
        put("Wolf", new HashMap<>(){{
            put("Horse", 10);
            put("Deer", 15);
            put("Rabbit", 60);
            put("Mouse", 80);
            put("Goat", 60);
            put("Sheep", 70);
            put("Hog", 15);
            put("Buffalo", 10);
            put("Duck", 40);
        }});
        put("Boa", new HashMap<>(){{
            put("Fox",15);
            put("Rabbit", 20);
            put("Mouse", 40);
            put("Duck", 10);
        }});
        put("Fox", new HashMap<>(){{
            put("Rabbit", 70);
            put("Mouse", 90);
            put("Duck", 60);
            put("Caterpillar", 40);
        }});
        put("Bear", new HashMap<>(){{
            put("Boa", 70);
            put("Horse", 40);
            put("Deer", 80);
            put("Rabbit", 80);
            put("Mouse", 90);
            put("Goat", 70);
            put("Sheep", 70);
            put("Hog", 50);
            put("Buffalo", 20);
            put("Duck", 10);
        }});
        put("Eagle", new HashMap<>(){{
            put("Fox", 10);
            put("Rabbit", 90);
            put("Mouse", 90);
            put("Duck", 80);
        }});
        put("Mouse", new HashMap<>(){{
            put("Caterpillar", 90);
        }});
        put("Hog", new HashMap<>(){{
            put("Mouse", 50);
            put("Caterpillar", 90);
        }});
        put("Duck", new HashMap<>(){{
            put("Caterpillar", 90);
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
