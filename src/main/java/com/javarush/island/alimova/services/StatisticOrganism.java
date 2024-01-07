package com.javarush.island.alimova.services;

import com.javarush.island.alimova.configure.SettingsEntity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class StatisticOrganism {

    private final CopyOnWriteArrayList<Long> numberOfOrganism = new CopyOnWriteArrayList<>();

    {
        for (int i = 0; i < SettingsEntity.nameOrganism.length; i++) {
            numberOfOrganism.add(0L);
        }
    }

    public void addNewOrganism(int index) {
        Long number = numberOfOrganism.get(index);
        numberOfOrganism.set(index, ++number);
    }

    public List<Long> getStatistic() {
        return numberOfOrganism.subList(0, numberOfOrganism.size());
    }

    public void printStatistic() {
        System.out.print("Statistic: {");
        for (int i = 0; i < SettingsEntity.nameOrganism.length; i++) {
            System.out.print(" " + SettingsEntity.nameOrganism[i] + " " + numberOfOrganism.get(i));
        }
        System.out.println(" }");
    }


}
