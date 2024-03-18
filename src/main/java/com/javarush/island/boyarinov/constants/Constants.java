package com.javarush.island.boyarinov.constants;

import com.javarush.island.boyarinov.entities.organism.Organisms;
import com.javarush.island.boyarinov.entities.organism.animals.herbivores.*;
import com.javarush.island.boyarinov.entities.organism.animals.insects.Caterpillar;
import com.javarush.island.boyarinov.entities.organism.animals.predators.*;
import com.javarush.island.boyarinov.entities.organism.plants.Grass;

import java.util.HashMap;
import java.util.Map;

public final class Constants {

    private Constants() {
    }

    public static final int ROW = 100;
    public static final int COLUMN = 30;
    public static final int CORE_POOL_SIZE = 4;
    public static final int PERIOD = 200;
    public static final double PERCENT_LOSE_WEIGHT = 0.5;
    public static final double PERCENT_NORMAL_WEIGHT = 90;
    public static final double HUNDRED_PERCENT = 100;
    public static final int CHANCE_FILLING = 10;
    public static final int NUMBER_ANIMAL_FOR_REPRODUCTION = 2;

    public static final String[] ANIMAL_NAME = new String[]{
            "Wolf", "Snake", "Fox", "Bear", "Eagle", "Horse", "Deer", "Rabbit", "Mouse",
            "Goat", "Sheep", "Boar", "Buffalo", "Duck", "Caterpillar", "Grass"
    };

    public static final String[] ANIMAL_ICON = new String[]{
            "🐺", "🐍", "🦊", "🐻", "🦅", "🐎", "🦌", "🐇", "🐁", "🐐", "🐑", "🐗", "🐃", "🦆", "🐛", "🌿"
    };

    private static final Map<String, Integer> ANIMAL_INDEX_IN_TABLE = new HashMap<>();

    static {
        for (int i = 0; i < ANIMAL_NAME.length; i++) {
            ANIMAL_INDEX_IN_TABLE.put(ANIMAL_NAME[i], i);
        }
    }


    @SuppressWarnings("unchecked")
    public static final Class<? extends Organisms>[] ANIMAL_CLASS_NAME = new Class[]{
            Wolf.class, Snake.class, Fox.class, Bear.class, Eagle.class, Horse.class, Deer.class,
            Rabbit.class, Mouse.class, Goat.class, Sheep.class, Boar.class, Buffalo.class,
            Duck.class, Caterpillar.class, Grass.class
    };

    private static final int[][] PROBABILITY_OF_BEING_EATEN = new int[][]{
            {0, 0, 0, 0, 0, 10, 15, 60, 80, 60, 70, 15, 10, 40, 0, 0},
            {0, 0, 15, 0, 0, 0, 0, 20, 40, 0, 0, 0, 0, 10, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 70, 90, 0, 0, 0, 0, 60, 40, 0},
            {0, 80, 0, 0, 0, 40, 80, 80, 90, 70, 70, 50, 20, 10, 0, 0},
            {0, 0, 10, 0, 0, 0, 0, 90, 90, 0, 0, 0, 0, 80, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 50, 0, 0, 0, 0, 0, 90, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100}

    };

    private static final Map<String, Map<String, Integer>> PROBABILITY_BEING_EATEN = new HashMap<>();

    static {
        int[][] probabilityTable = Constants.PROBABILITY_OF_BEING_EATEN;
        for (int i = 0; i < probabilityTable.length; i++) {
            String animalName = ANIMAL_NAME[i];
            Map<String, Integer> foodMap = new HashMap<>();
            PROBABILITY_BEING_EATEN.put(animalName, foodMap);
            for (int j = 0; j < probabilityTable[i].length; j++) {
                int percent = probabilityTable[i][j];
                if (percent == 0) {
                    continue;
                }
                String foodName = ANIMAL_NAME[j];
                foodMap.put(foodName, percent);
            }
        }
    }

    public static final double[] MAX_WEIGHT = new double[]{
            50, 15, 8, 500, 6, 400, 300, 2, 0.05, 60, 70, 400, 700, 1, 0.01, 1
    };

    public static final int[] MAX_OF_ANIMALS_TO_CELL = new int[]{
            30, 30, 30, 5, 20, 20, 20, 150, 500, 140, 140, 50, 10, 200, 1000, 200
    };

    public static final int[] TRAVEL_SPEED = new int[]{
            3, 1, 2, 2, 3, 4, 4, 2, 1, 3, 3, 2, 3, 4, 0, 0
    };

    public static final double[] NUMBER_KG_FOR_FULL_SATURATION = new double[]{
            8, 3, 2, 80, 1, 60, 50, 0.45, 0.01, 10, 15, 50, 100, 0.15, 0, 0
    };

    public static Map<String, Integer> getAnimalIndexInTable() {
        return new HashMap<>(ANIMAL_INDEX_IN_TABLE);
    }

    public static Map<String, Map<String, Integer>> getProbabilityBeingEaten() {
        return new HashMap<>(PROBABILITY_BEING_EATEN);
    }
}

