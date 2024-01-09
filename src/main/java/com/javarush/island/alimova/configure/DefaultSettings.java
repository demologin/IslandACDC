package com.javarush.island.alimova.configure;

import com.javarush.island.alimova.entity.alive.animals.herbivores.*;
import com.javarush.island.alimova.entity.alive.animals.predators.*;
import com.javarush.island.alimova.entity.alive.plants.Grass;

import java.util.HashMap;
import java.util.Map;

public class DefaultSettings {

    public static String[] nameOrganism = {
            "Wolf", "Anaconda", "Fox", "Bear", "Eagle",
            "Horse", "Deer", "Rabbit", "Mouse", "Goat",
            "Sheep", "Boar", "Buffalo", "Duck", "Caterpillar", "Grass"};
    //может, сделать всё капсом, чтобы быть уверенным?

    public static Class<?>[] classNameOrganism = new Class[]{
            Wolf.class, Anaconda.class, Fox.class, Bear.class, Eagle.class,
            Horse.class, Deer.class, Rabbit.class, Mouse.class, Goat.class,
            Sheep.class, Boar.class, Buffalo.class, Duck.class, Caterpillar.class,
            Grass.class};

    public static Map<String, Integer> organismMap = new HashMap<>();

    static {
        for (int i = 0; i < nameOrganism.length; i++) {
            organismMap.put(nameOrganism[i], i);
        }
    }

    public static int[][] eatingTable = {
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

    public static double[] limitWeightOrganism = {
            50, 15, 8, 500, 6, 400, 300, 2, 0.05, 60, 70, 400, 700, 1, 0.01, 1
    };

    public static int[] maxAmountOrganism = {
            30, 30, 30, 5, 20, 20, 20, 150, 500, 140, 140, 50, 10, 200, 1000, 200
    };

    public static int[] maxSpeedOrganism = {
            3, 1, 2, 2, 3, 4, 4, 2, 1, 3, 3, 2, 3, 4, 0, 0
    };

    public static double[] maxFoodWeightOrganism = {
            8, 3, 2, 80, 1, 60, 50, 0.45, 0.01, 10, 15, 50, 0.15, 0, 0
    };
    private static void SettingsEntity() {
    }

    public static int getIndexOrganism(String name) {
        return organismMap.get(name);
    }
}
