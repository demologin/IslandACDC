package com.javarush.island.berezovskiy.Constants;

import com.javarush.island.berezovskiy.Entities.Organism.OrganismTypeEnum;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final String HERBIVORE = "HERBIVORE";
    public static final String PREDATOR = "PREDATOR";
    public static final String PLANT = "PLANT";
    public static final String EMPTY = "\uD83E\uDDF1";
    public static final String UNNAMED = "UNNAMED";
    public static final String TOTAL_ORGANISM_AMOUNT = "Total amount = ";




    //PREDATORS//
    public static final String WOLF = "WOLF";
    public static final String BOA = "BOA";
    public static final String FOX = "FOX";
    public static final String BEAR = "BEAR";
    public static final String EAGLE = "EAGLE";
    public static final Map<Integer, String> PREDATORS_MAP = Map.of(
            0, WOLF,
            1, BOA,
            2, FOX,
            3, BEAR,
            4, EAGLE
    );
    //HERBIVORES//
    public static final String HORSE = "HORSE";
    public static final String DEER = "DEER";
    public static final String RABBIT = "RABBIT";
    public static final String MOUSE = "MOUSE";
    public static final String GOAT = "GOAT";
    public static final String SHEEP = "SHEEP";
    public static final String BOAR = "BOAR";
    public static final String BUFFALO = "BUFFALO";
    public static final String DUCK = "DUCK";
    public static final String CATERPILLAR = "CATERPILLAR";
    public static final Map<Integer, String> HERBIVORES_MAP = Map.of(
            0, HORSE,
            1, DEER,
            2, RABBIT,
            3, MOUSE,
            4, GOAT,
            5, SHEEP,
            6, BOAR,
            7, BUFFALO,
            8, DUCK,
            9, CATERPILLAR
    );
    //PLANTS//
    public static final String GRASS = "GRASS";
    public static final Map<Integer, String> PLANTS_MAP = Map.of(
            0, GRASS);
    public static final Map<OrganismTypeEnum, Map<Integer, String>> ORGANISM_TYPE_MAP = Map.of(
            OrganismTypeEnum.HERBIVORE, HERBIVORES_MAP,
            OrganismTypeEnum.PREDATOR, PREDATORS_MAP,
            OrganismTypeEnum.PLANT, PLANTS_MAP
    );
    public static final Map<String, String> IMAGE_MAP = new HashMap<>();
    static {
        IMAGE_MAP.put(WOLF, "\uD83D\uDC3A");
        IMAGE_MAP.put(BOA, "\uD83D\uDC0D");
        IMAGE_MAP.put(FOX,"\uD83E\uDD8A");
        IMAGE_MAP.put(BEAR, "\uD83D\uDC3B");
        IMAGE_MAP.put(EAGLE, "\uD83E\uDD85");
        IMAGE_MAP.put(HORSE, "\uD83D\uDC34");
        IMAGE_MAP.put(DEER, "\uD83E\uDD8C");
        IMAGE_MAP.put(RABBIT, "\uD83D\uDC30");
        IMAGE_MAP.put(MOUSE, "\uD83D\uDC2D");
        IMAGE_MAP.put(GOAT, "\uD83D\uDC10");
        IMAGE_MAP.put(SHEEP, "\uD83D\uDC11");
        IMAGE_MAP.put(BOAR, "\uD83D\uDC17");
        IMAGE_MAP.put(BUFFALO, "\uD83D\uDC03");
        IMAGE_MAP.put(DUCK, "\uD83E\uDD86");
        IMAGE_MAP.put(CATERPILLAR, "\uD83D\uDC1B");
        IMAGE_MAP.put(GRASS, "\uD83C\uDF3B");
    }
}
