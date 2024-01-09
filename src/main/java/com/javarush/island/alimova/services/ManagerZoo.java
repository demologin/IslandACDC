package com.javarush.island.alimova.services;

import com.javarush.island.alimova.configure.DefaultSettings;
import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.entity.map.StatisticOrganism;
import com.javarush.island.alimova.entity.map.Table;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ManagerZoo {

    private StatisticOrganism statisticOrganism;
    private Table table;

    private FabricOrganism fabric;

    public void bootstrap() {

        statisticOrganism = new StatisticOrganism();
        table = new Table(statisticOrganism);
        table.createCell();
        fabric = new FabricOrganism(statisticOrganism);
        //fabric.createCopyBase();
        for (String name :
                DefaultSettings.nameOrganism) {
            int randomCounter = ThreadLocalRandom.current().nextInt(0, 10);
            for (int i = 0; i < randomCounter; i++) {
                Organism organism = fabric.createNewInstanceOrganism(name);
                int indexX = ThreadLocalRandom.current().nextInt(0, 10);
                int indexY = ThreadLocalRandom.current().nextInt(0, 10);
                table.addNewOrganism(indexX, indexY, organism);
            }

            //System.out.println(organism);
        }
        table.printTable();
        fabric.printStatisticOrganism();

    }

    public void startLive() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(100);
        for (int i = 0; i < table.height; i++) {
            for (int j = 0; j < table.width; j++) {
                executorService.scheduleAtFixedRate(new OrganismWorker(table.getCurrentCell(i, j)), 0, 1, TimeUnit.SECONDS);
            }
        }

        while(true) {
            try {
                Thread.sleep(2000);
                fabric.printStatisticOrganism();
                table.printTable();
                System.out.println();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
