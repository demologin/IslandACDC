package com.javarush.island.alimova.entity.map;

import com.javarush.island.alimova.configure.DefaultSettings;
import com.javarush.island.alimova.configure.SettingsEntity;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class StatisticOrganism {

    private final SettingsEntity settings;

    private final ReentrantLock locker = new ReentrantLock();

    public StatisticOrganism(SettingsEntity settings) {
        this.settings = settings;
    }

    private final CopyOnWriteArrayList<Long> amountOfAliveOrganism = new CopyOnWriteArrayList<>();

    private final CopyOnWriteArrayList<Long> amountOfDeathOrganism = new CopyOnWriteArrayList<>();

    {
        for (int i = 0; i < DefaultSettings.nameOrganism.length; i++) {
            amountOfAliveOrganism.add(0L);
            amountOfDeathOrganism.add(0L);
        }
    }

    public void addNewOrganism(int index) {    //правильно ли здесь ставить?
        locker.lock();
        try {
            Long number = amountOfAliveOrganism.get(index);
            amountOfAliveOrganism.set(index, ++number);
        } finally {
            locker.unlock();
        }

    }

    public void deleteOrganism(int index) {
        locker.lock();
        try {
            Long number = amountOfAliveOrganism.get(index);
            amountOfAliveOrganism.set(index, --number);

            number = amountOfDeathOrganism.get(index);
            amountOfDeathOrganism.set(index, ++number);
        } finally {
            locker.unlock();
        }

    }

    public void printStatistic() {
        System.out.println("Statistic: {");
        for (int i = 0; i < settings.nameOrganism.length / 2; i++) {
            System.out.print(settings.nameOrganism[i] + " - " + amountOfAliveOrganism.get(i) + " ");
        }
        System.out.println();
        for (int i = settings.nameOrganism.length / 2; i < settings.nameOrganism.length; i++) {
            System.out.print(" " + settings.nameOrganism[i] + " " + amountOfAliveOrganism.get(i));
        }
        System.out.println(" }\n");

        System.out.println("Number of deaths: {");
        for (int i = 0; i < settings.nameOrganism.length / 2; i++) {
            System.out.print(settings.nameOrganism[i] + " - " + amountOfDeathOrganism.get(i) + " ");
        }
        System.out.println();
        for (int i = settings.nameOrganism.length / 2; i < settings.nameOrganism.length; i++) {
            System.out.print(settings.nameOrganism[i] + " - " + amountOfDeathOrganism.get(i) + " ");
        }
        System.out.println(" }\n");
    }


}
