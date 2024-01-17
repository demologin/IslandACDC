package com.javarush.island.alimova.services;


import com.javarush.island.alimova.configure.SettingsEntity;
import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.entity.alive.animals.Animal;
import com.javarush.island.alimova.entity.alive.plants.Plant;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class FabricOrganism {

    private final ArrayList<Organism> cloneBase = new ArrayList<>();

    private final SettingsEntity settings;


    public FabricOrganism(SettingsEntity settings) {
        this.settings = settings;
    }

    private Organism createNewExampleOrganism(String name){

        int indexOrganism = settings.getIndexOrganism(name);
        Class<?> classOrganism = settings.classNameOrganism[indexOrganism];
        Organism result;
        try {
            if (Plant.class.isAssignableFrom(classOrganism)) {
                Constructor<?> constructor = classOrganism.getDeclaredConstructor(double.class, int.class);
                double weight = settings.limitWeightOrganism[indexOrganism];
                int maxAmount = settings.maxAmountOrganism[indexOrganism];
                result = (Organism) constructor.newInstance(weight, maxAmount);
            } else if (Animal.class.isAssignableFrom(classOrganism)) {
                Constructor<?> constructor = classOrganism.getDeclaredConstructor(double.class, int.class, int.class,
                        double.class, boolean.class);
                double weight = settings.limitWeightOrganism[indexOrganism];
                int maxAmount = settings.maxAmountOrganism[indexOrganism];
                int maxSpeed = settings.maxSpeedOrganism[indexOrganism];
                double maxFoodWeight = settings.maxFoodWeightOrganism[indexOrganism];
                result = (Organism) constructor.newInstance(weight, maxAmount, maxSpeed, maxFoodWeight, false);
            }
            else {
                throw new RuntimeException();   //какой-то свой эксепшен надо
            }

        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InstantiationException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public void createCopyBase() {
        for (String name :
                settings.nameOrganism) {
            Organism organism = this.createNewExampleOrganism(name);
            cloneBase.add(organism);
        }
    }

    public Organism createNewInstanceOrganism(Class<?> classOrganism) {
        int indexOrganism = settings.getIndexOrganism(classOrganism);
        Organism organism;
        try {
            organism = cloneBase.get(indexOrganism).clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return organism;
    }

}
