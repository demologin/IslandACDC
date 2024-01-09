package com.javarush.island.alimova.configure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SettingsEntity implements Serializable {

    private static String pathSetting = "alimova" + File.separator + "setting.yaml";
    private static String pathToResources = System.getProperty("user.dir") + File.separator + "src"
            + File.separator + "main" + File.separator + "resources";

    public static void main(String[] args) {

        String pathFile = pathToResources + File.separator + pathSetting;
        System.out.println(pathFile);
        File file = new File(pathFile);
        ObjectMapper objectMapper = new YAMLMapper();
        try {
            SettingsEntity settings = objectMapper.readValue(file, SettingsEntity.class);
            System.out.println(settings.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] nameOrganism;
    //может, сделать всё капсом, чтобы быть уверенным?
    //может, сделать всё final и передавать в классы...

    public Class<?>[] classNameOrganism;

    public Map<String, Integer> organismMap = new HashMap<>();

    public int[][] eatingTable;

    public double[] limitWeightOrganism;

    public int[] maxAmountOrganism;

    public int[] maxSpeedOrganism;

    public double[] maxFoodWeightOrganism;


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
