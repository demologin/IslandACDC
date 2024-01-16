package com.javarush.island.alimova.configure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.exception.GameException;
import lombok.SneakyThrows;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SettingsEntity implements Serializable {

    private final String pathSetting = "alimova" + File.separator + "setting.yaml";

    public String[] nameOrganism;

    public String[] iconOrganism;

    public Class<?>[] classNameOrganism;

    public Map<String, Integer> organismMap = new HashMap<>();

    public Map<Class<?>, Integer> organismMapClass = new HashMap<>();

    public int initialNumberOfPlants;

    public int[][] eatingTable;

    public double[] limitWeightOrganism;

    public int[] maxAmountOrganism;

    public int[] maxSpeedOrganism;

    public double[] maxFoodWeightOrganism;

    public int heightTable;
    public int widthTable;

    public int viewHeightTable;

    public int viewWidthTable;

    public int minRandomOrganism;

    public int maxRandomOrganism;

    public int periodGame;

    public int maxLifeCycle = 30;


    public void setDefaultSettings() {
        nameOrganism = DefaultSettings.NAME_ORGANISM;
        iconOrganism = DefaultSettings.ICON_ORGANISM;
        classNameOrganism = DefaultSettings.CLASS_NAME_ORGANISM;
        eatingTable = DefaultSettings.EATING_TABLE;
        limitWeightOrganism = DefaultSettings.LIMIT_WEIGHT_ORGANISM;
        maxAmountOrganism = DefaultSettings.MAX_AMOUNT_ORGANISM;
        maxSpeedOrganism = DefaultSettings.MAX_SPEED_ORGANISM;
        maxFoodWeightOrganism = DefaultSettings.MAX_FOOD_WEIGHT_ORGANISM;
        heightTable = DefaultSettings.HEIGHT_TABLE;
        widthTable = DefaultSettings.WIDTH_TABLE;
        viewHeightTable = DefaultSettings.VIEW_HEIGHT_TABLE;
        viewWidthTable = DefaultSettings.VIEW_WIDTH_TABLE;
        initialNumberOfPlants = DefaultSettings.INITIAL_NUMBER_OF_PLANTS;
        minRandomOrganism = DefaultSettings.MIN_RANDOM_ORGANISM;
        maxRandomOrganism = DefaultSettings.MAX_RANDOM_ORGANISM;
        periodGame = DefaultSettings.PERIOD_GAME;
        maxLifeCycle = DefaultSettings.MAX_LIFE_CYCLE;
    }

    @SneakyThrows
    public void setSettingsFromFile() {
        ObjectMapper objectMapper = new YAMLMapper();
        ObjectReader objectReader = objectMapper.readerForUpdating(this);
        URL resource = SettingsEntity.class.getClassLoader().getResource(pathSetting);
        if (Objects.nonNull(resource)) {
            objectReader.readValue(resource.openStream());
        } else {
            System.err.println(DefaultSettings.MESSAGE_FILE_NOT_FOUND);
        }

    }

    public void initializationField() {
        checkClassOrganism();
        try {
            for (int i = 0; i < nameOrganism.length; i++) {
                organismMap.put(nameOrganism[i], i);
            }

            for (int i = 0; i < classNameOrganism.length; i++) {
                organismMapClass.put(classNameOrganism[i], i);
            }
        } catch (ClassCastException | NullPointerException | IllegalArgumentException e) {
            throw new GameException(DefaultSettings.MESSAGE_ERROR_NAME_ORGANISM, e);
        }

    }

    private void checkClassOrganism() {
        for(Class<?> className: classNameOrganism) {
            if (!Organism.class.isAssignableFrom(className)) {
                throw  new GameException(DefaultSettings.MESSAGE_ERROR_CLASS);
            }
        }
    }

    public int getIndexOrganism(String name) {
        return organismMap.get(name);
    }

    public int getIndexOrganism(Class<?> classOrganism) {
        return organismMapClass.get(classOrganism);
    }

    @Override
    public String toString() {
        return "SettingsEntity{" +
                "nameOrganism=" + Arrays.toString(nameOrganism) +
                ", classNameOrganism=" + Arrays.toString(classNameOrganism) +
                ", organismMap=" + organismMap +
                ", eatingTable=" + Arrays.toString(eatingTable) +
                ", limitWeightOrganism=" + Arrays.toString(limitWeightOrganism) +
                ", maxAmountOrganism=" + Arrays.toString(maxAmountOrganism) +
                ", maxSpeedOrganism=" + Arrays.toString(maxSpeedOrganism) +
                ", maxFoodWeightOrganism=" + Arrays.toString(maxFoodWeightOrganism) +
                '}';
    }
}
