package com.javarush.island.alimova.configure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.javarush.island.alimova.entity.alive.animals.herbivores.*;
import com.javarush.island.alimova.entity.alive.animals.predators.*;
import com.javarush.island.alimova.entity.alive.plants.Grass;
import com.javarush.island.khmelov.config.Setting;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SettingsEntity implements Serializable {

    private static final String pathSetting = "alimova" + File.separator + "setting.yaml";
    private static final String pathToResources = System.getProperty("user.dir") + File.separator + "src"
            + File.separator + "main" + File.separator + "resources";


    public String[] nameOrganism;

    public String[] iconOrganism;

    public Class<?>[] classNameOrganism;

    public Map<String, Integer> organismMap = new HashMap<>();

    public Map<Class<?>, Integer> organismMapClass = new HashMap<>();     //нужно определиться, как хранить

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


    public void setDefaultSettings() {
        nameOrganism = DefaultSettings.nameOrganism;
        iconOrganism = DefaultSettings.iconOrganism;
        classNameOrganism = DefaultSettings.classNameOrganism;
        eatingTable = DefaultSettings.eatingTable;
        limitWeightOrganism = DefaultSettings.limitWeightOrganism;
        maxAmountOrganism = DefaultSettings.maxAmountOrganism;
        maxSpeedOrganism = DefaultSettings.maxSpeedOrganism;
        maxFoodWeightOrganism = DefaultSettings.maxFoodWeightOrganism;
        heightTable = DefaultSettings.heightTable;
        widthTable = DefaultSettings.widthTable;
        viewHeightTable = DefaultSettings.viewHeightTable;
        viewWidthTable = DefaultSettings.viewWidthTable;
        initialNumberOfPlants = DefaultSettings.initialNumberOfPlants;
        minRandomOrganism = DefaultSettings.minRandomOrganism;
        maxRandomOrganism = DefaultSettings.maxRandomOrganism;
    }

    @SneakyThrows
    public void setSettingsFromFile() {
        ObjectMapper objectMapper = new YAMLMapper();
        ObjectReader objectReader = objectMapper.readerForUpdating(this);
        URL resource = SettingsEntity.class.getClassLoader().getResource(pathSetting);
        if (Objects.nonNull(resource)) {
            objectReader.readValue(resource.openStream());
        }

    }

    public void initializationField() {
        for (int i = 0; i < nameOrganism.length; i++) {
            organismMap.put(nameOrganism[i], i);
        }

        for (int i = 0; i < classNameOrganism.length; i++) {
            organismMapClass.put(classNameOrganism[i], i);
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
