package com.javarush.island.maikov.Constants;

public class Constants {
    public static final String WOLF = "\uD83D\uDC3A";
    public static final String RABBIT = "\uD83D\uDC07";
    public static final String BOA = "\uD83D\uDC0D";
    public static final String FOX = "\uD83E\uDD8A";
    public static final String BEAR = "\uD83D\uDC3B";
    public static final String EAGLE = "\uD83E\uDD85";
    public static final String HORSE = "\uD83D\uDC0E";
    public static final String DEER = "\uD83D\uDC0E";
    public static final String MOUSE = "\uD83D\uDC01";
    public static final String GOAT = "\uD83D\uDC10";
    public static final String SHEEP = "\uD83D\uDC11";
    public static final String BOAR = "\uD83D\uDC17";
    public static final String BUFFALO = "\uD83D\uDC03";
    public static final String DUCK = "\uD83E\uDD86";
    public static final String CATERPILLAR = "\uD83D\uDC1B";
    public static final String CLOVER = "♣";
    public static final double MIN_LIFE_FOR_REPRODUCE = 0.7;
    public static final double MINUS_LIFE_FOR_MOVE_AND_REPRODUCE = 0.2;
    public static final int MIN_VALUE_OF_ANIMALS_ON_ONE_CELL = 2;
    public static final int MAX_VALUE_OF_ANIMALS_ON_ONE_CELL = 10;
    public static final int MIN_VALUE_OF_GRASS_ON_ONE_CELL = 0;
    public static final int MAX_VALUE_OF_GRASS_ON_ONE_CELL = 5;
    public static final int MIN_RANDOM_PREDATORS_ON_ONE_CELL = 1;
    public static final int MAX_RANDOM_PREDATORS_ON_ONE_CELL = 5;
    public static final int MIN_RANDOM_HERBIVORES_ON_ONE_CELL = 1;
    public static final int MAX_RANDOM_HERBIVORES_ON_ONE_CELL = 10;
    public static final int[][] SET_PROBABLY_TABLE = {
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
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };

}
