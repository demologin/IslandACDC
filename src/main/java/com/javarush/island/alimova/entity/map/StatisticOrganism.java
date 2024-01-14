package com.javarush.island.alimova.entity.map;

import com.javarush.island.alimova.configure.DefaultSettings;
import com.javarush.island.alimova.configure.SettingsEntity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class StatisticOrganism {

    private final SettingsEntity settings;

    public StatisticOrganism(SettingsEntity settings) {
        this.settings = settings;
    }

    private final CopyOnWriteArrayList<Long> numberOfOrganism = new CopyOnWriteArrayList<>();

    {
        for (int i = 0; i < DefaultSettings.nameOrganism.length; i++) {
            numberOfOrganism.add(0L);
        }
    }

    public synchronized void addNewOrganism(int index) {    //правильно ли здесь ставить?
        Long number = numberOfOrganism.get(index);
        numberOfOrganism.set(index, ++number);
    }

    public synchronized void deleteOrganism(int index) {
        Long number = numberOfOrganism.get(index);
        numberOfOrganism.set(index, --number);
    }

    public void printStatistic() {
        System.out.print("Statistic: {");
        for (int i = 0; i < settings.nameOrganism.length; i++) {
            System.out.print(" " + settings.nameOrganism[i] + " " + numberOfOrganism.get(i));
        }
        System.out.println(" }");
    }


}
