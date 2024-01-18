package com.javarush.island.boyarinov.util;

import com.javarush.island.boyarinov.constants.Constants;
import com.javarush.island.boyarinov.entities.organism.Organisms;
import com.javarush.island.boyarinov.entities.organism.animals.herbivores.Sheep;

import java.util.HashMap;
import java.util.Map;

public final class Limit {

    private static final Map<Class<? extends Organisms>, Integer> animalIndexInTable = new HashMap<>();
    private static final Map<Class<? extends Organisms>, String> animalName = new HashMap<>();
    private static final Map<Class<? extends Organisms>, Double> maxWeight = new HashMap<>();
    private static final Map<Class<? extends Organisms>, Integer> maxOfAnimalsToCell = new HashMap<>();
    private static final Map<Class<? extends Organisms>, Integer> travelSpeed = new HashMap<>();
    private static final Map<Class<? extends Organisms>, Double> numberKgForFullSaturation = new HashMap<>();

    private Limit() {
    }

    static {
        Class<? extends Organisms>[] animalClassName = Constants.ANIMAL_CLASS_NAME;
        for (int i = 0; i < animalClassName.length; i++) {
            animalIndexInTable.put(animalClassName[i], i);
            animalName.put(animalClassName[i], Constants.ANIMAL_NAME[i]);
            maxWeight.put(animalClassName[i], Constants.MAX_WEIGHT[i]);
            maxOfAnimalsToCell.put(animalClassName[i], Constants.MAX_OF_ANIMALS_TO_CELL[i]);
            travelSpeed.put(animalClassName[i], Constants.TRAVEL_SPEED[i]);
            numberKgForFullSaturation.put(animalClassName[i], Constants.NUMBER_KG_FOR_FULL_SATURATION[i]);
        }


    }

    public static Map<Class<? extends Organisms>, Integer> getMaxOfAnimalsToCell() {
        return maxOfAnimalsToCell;
    }

    public static Map<Class<? extends Organisms>, Integer> getTravelSpeed() {
        return travelSpeed;
    }

    public static Map<Class<? extends Organisms>, Double> getMaxWeight() {
        return maxWeight;
    }

    public static Map<Class<? extends Organisms>, Double> getNumberKgForFullSaturation() {
        return numberKgForFullSaturation;
    }

    public static Map<Class<? extends Organisms>, Integer> getAnimalIndexInTable() {
        return animalIndexInTable;
    }

    public static Map<Class<? extends Organisms>, String> getAnimalName() {
        return animalName;
    }
}
