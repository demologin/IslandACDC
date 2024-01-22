package com.javarush.island.berezovskiy.Configs;

import com.javarush.island.berezovskiy.Constants.Constants;

import java.util.Map;

public class Configs {
    public static final int TIME_FOR_WAITING = 2;
    public static final int ISLAND_HEIGHT = 4;
    public static final int ISLAND_WIDTH = 4;
    public static final int ANIMAL_LIMIT_IN_CELL = 4;
    public static final int PREDATORS_COUNT_IN_START = 10;
    public static final int HERBIVORES_COUNT_IN_START = 12;
    public static final int PLANTS_COUNT_IN_START = 6;
    public static final int MIN_ANIMAL_IN_FLOCK = 2;
    public static final Map<String, Integer> ORGANISMS_COUNT_IN_START = Map.of(
            Constants.PREDATOR, PREDATORS_COUNT_IN_START,
            Constants.HERBIVORE,HERBIVORES_COUNT_IN_START,
            Constants.PLANT,PLANTS_COUNT_IN_START
    );




}
