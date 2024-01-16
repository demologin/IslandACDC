package com.javarush.island.alimova.configure;

import com.javarush.island.alimova.entity.alive.animals.herbivores.*;
import com.javarush.island.alimova.entity.alive.animals.predators.*;
import com.javarush.island.alimova.entity.alive.plants.Grass;

public class DefaultSettings {

    public static final String[] NAME_ORGANISM = {
            "Wolf", "Anaconda", "Fox", "Bear", "Eagle",
            "Horse", "Deer", "Rabbit", "Mouse", "Goat",
            "Sheep", "Boar", "Buffalo", "Duck", "Caterpillar", "Grass"};
    //может, сделать всё капсом, чтобы быть уверенным?

    public static final String[] ICON_ORGANISM = {
            "\uD83D\uDC3A", "\uD83D\uDC0D", "\uD83E\uDD8A", "\uD83D\uDC3B",
            "\uD83E\uDD85", "\uD83D\uDC0E", "\uD83E\uDD8C", "\uD83D\uDC07",
            "\uD83D\uDC01", "\uD83D\uDC10", "\uD83D\uDC11", "\uD83D\uDC17",
            "\uD83D\uDC03", "\uD83E\uDD86", "\uD83D\uDC1B", "\uD83C\uDF31"};


    public static final Class<?>[] CLASS_NAME_ORGANISM = new Class[]{
            Wolf.class, Anaconda.class, Fox.class, Bear.class, Eagle.class,
            Horse.class, Deer.class, Rabbit.class, Mouse.class, Goat.class,
            Sheep.class, Boar.class, Buffalo.class, Duck.class, Caterpillar.class,
            Grass.class};

    public static final int INITIAL_NUMBER_OF_PLANTS = 200;

    public static final int MIN_RANDOM_ORGANISM = 2;

    public static final int MAX_RANDOM_ORGANISM = 15;

    public static final int PERIOD_GAME = 1;

    public static final int[][] EATING_TABLE = {
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

    public static final double[] LIMIT_WEIGHT_ORGANISM = {
            50, 15, 8, 500, 6, 400, 300, 2, 0.05, 60, 70, 400, 700, 1, 0.01, 1
    };

    public static final int[] MAX_AMOUNT_ORGANISM = {
            30, 30, 30, 5, 20, 20, 20, 150, 500, 140, 140, 50, 10, 200, 1000, 200
    };

    public static final int[] MAX_SPEED_ORGANISM = {
            3, 1, 2, 2, 3, 4, 4, 2, 1, 3, 3, 2, 3, 4, 0, 0
    };

    public static final double[] MAX_FOOD_WEIGHT_ORGANISM = {
            8, 3, 2, 80, 1, 60, 50, 0.45, 0.01, 10, 15, 50, 0.15, 0.005, 0
    };

    public static final int HEIGHT_TABLE = 10;
    public static final int WIDTH_TABLE = 10;

    public static final int VIEW_HEIGHT_TABLE = 5;

    public static final int VIEW_WIDTH_TABLE = 5;

    public static final int MAX_LIFE_CYCLE = 30;

    public static final String MESSAGE_FINAL_GAME = "Finish game!";

    public static final String MESSAGE_TIME_OUT_GAME = "There is not enough time for processing.\nPlease change your settings.";

}
