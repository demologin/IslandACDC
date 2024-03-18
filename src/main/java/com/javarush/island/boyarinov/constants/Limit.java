package com.javarush.island.boyarinov.constants;

import java.util.HashMap;
import java.util.Map;

public final class Limit {

    private Limit() {
    }


    private static final Map<String, Double> MAX_WEIGHT = new HashMap<>();
    private static final Map<String, Integer> MAX_OF_ANIMALS_TO_CELL = new HashMap<>();
    private static final Map<String, Integer> TRAVEL_SPEED = new HashMap<>();
    private static final Map<String, Double> NUMBER_KG_FOR_FULL_SATURATION = new HashMap<>();

    static {
        String[] arrayAnimalName = Constants.ANIMAL_NAME;
        for (int i = 0; i < arrayAnimalName.length; i++) {
            MAX_WEIGHT.put(arrayAnimalName[i], Constants.MAX_WEIGHT[i]);
            MAX_OF_ANIMALS_TO_CELL.put(arrayAnimalName[i], Constants.MAX_OF_ANIMALS_TO_CELL[i]);
            TRAVEL_SPEED.put(arrayAnimalName[i], Constants.TRAVEL_SPEED[i]);
            NUMBER_KG_FOR_FULL_SATURATION.put(arrayAnimalName[i], Constants.NUMBER_KG_FOR_FULL_SATURATION[i]);
        }
    }

    public static Map<String, Double> getMaxWeight() {
        return new HashMap<>(MAX_WEIGHT);
    }

    public static Map<String, Integer> getMaxOfAnimalsToCell() {
        return new HashMap<>(MAX_OF_ANIMALS_TO_CELL);
    }

    public static Map<String, Integer> getTravelSpeed() {
        return new HashMap<>(TRAVEL_SPEED);
    }

    public static Map<String, Double> getNumberKgForFullSaturation() {
        return new HashMap<>(NUMBER_KG_FOR_FULL_SATURATION);
    }
}
