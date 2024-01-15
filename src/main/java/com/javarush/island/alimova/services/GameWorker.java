package com.javarush.island.alimova.services;

import com.javarush.island.alimova.configure.SettingsEntity;
import com.javarush.island.alimova.entity.map.StatisticOrganism;
import com.javarush.island.alimova.entity.map.Table;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GameWorker implements Runnable{

    private final Table tableGame;

    private final SettingsEntity settings;

    private final StatisticOrganism statisticOrganism;

    public GameWorker(Table tableGame, SettingsEntity settings, StatisticOrganism statisticOrganism) {
        this.tableGame = tableGame;
        this.settings = settings;
        this.statisticOrganism = statisticOrganism;
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(tableGame.height * tableGame.width);
        for (int i = 0; i < tableGame.height; i++) {
            for (int j = 0; j < tableGame.width; j++) {
                executorService.execute((new OrganismWorker(tableGame.getCurrentCell(i, j), settings)));
            }
        }
        executorService.shutdown();

        try {
            if(executorService.awaitTermination(1, TimeUnit.SECONDS)) {
                tableGame.printTable();
                System.out.println();
                statisticOrganism.printStatistic();
            } else {
                System.out.println("NO TIME!!!!!!");        //может, здесь кинуть exception
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
