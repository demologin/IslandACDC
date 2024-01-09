package com.javarush.island.alimova.services;

import com.javarush.island.alimova.configure.DefaultSettings;
import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.entity.alive.animals.Animal;
import com.javarush.island.alimova.entity.alive.plants.Plant;
import com.javarush.island.alimova.entity.map.StatisticOrganism;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CopyOnWriteArrayList;

public class FabricOrganism {

    private final CopyOnWriteArrayList<Organism> cloneBase = new CopyOnWriteArrayList<>();
    private final StatisticOrganism statisticOrganism;

    {
        createCopyBase();
    }

    public FabricOrganism(StatisticOrganism statisticOrganism) {
        this.statisticOrganism = statisticOrganism;
    }

    private Organism createNewExampleOrganism(String name){

        //может, создавать по индексу

        int indexOrganism = DefaultSettings.getIndexOrganism(name);
        Class<?> classOrganism = DefaultSettings.classNameOrganism[indexOrganism];
        Organism result = null;
        try {
            if (Plant.class.isAssignableFrom(classOrganism)) {
                Constructor<?> constructor = classOrganism.getDeclaredConstructor(double.class, int.class);
                double weight = DefaultSettings.limitWeightOrganism[indexOrganism];
                int maxAmount = DefaultSettings.maxAmountOrganism[indexOrganism];
                result = (Organism) constructor.newInstance(weight, maxAmount);
            } else if (Animal.class.isAssignableFrom(classOrganism)) {
                Constructor<?> constructor = classOrganism.getDeclaredConstructor(double.class, int.class, int.class,
                        double.class);
                double weight = DefaultSettings.limitWeightOrganism[indexOrganism];
                int maxAmount = DefaultSettings.maxAmountOrganism[indexOrganism];
                int maxSpeed = DefaultSettings.maxSpeedOrganism[indexOrganism];
                double maxFoodWeight = DefaultSettings.maxFoodWeightOrganism[indexOrganism];
                result = (Organism) constructor.newInstance(weight, maxAmount, maxSpeed, maxFoodWeight);
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
                DefaultSettings.nameOrganism) {
            Organism organism = this.createNewExampleOrganism(name);
            cloneBase.add(organism);
        }
    }

    public Organism createNewInstanceOrganism(String name) {
        int indexOrganism = DefaultSettings.getIndexOrganism(name);
        Organism organism = null;
        try {
            organism = cloneBase.get(indexOrganism).clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return organism;
    }

    public void printStatisticOrganism() {
        statisticOrganism.printStatistic();
    }



}
