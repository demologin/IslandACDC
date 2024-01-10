package com.javarush.island.alimova.services;

import com.javarush.island.alimova.configure.DefaultSettings;
import com.javarush.island.alimova.configure.SettingsEntity;
import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.entity.alive.plants.Plant;
import com.javarush.island.alimova.entity.map.StatisticOrganism;
import com.javarush.island.alimova.entity.map.Table;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ManagerZoo {

    private StatisticOrganism statisticOrganism;
    private SettingsEntity settings;
    private Table table;

    private FabricOrganism fabric;

    public void bootstrap() {

        setStartupSettings();
        for (String name :
                DefaultSettings.nameOrganism) {
            int randomCounter = ThreadLocalRandom.current().nextInt(300, 1000);
            for (int i = 0; i < randomCounter; i++) {
                Organism organism = fabric.createNewInstanceOrganism(name);
                int indexX = ThreadLocalRandom.current().nextInt(0, 200);
                int indexY = ThreadLocalRandom.current().nextInt(0, 10);
                table.addNewOrganism(indexX, indexY, organism);     //тут игнорируем возвращаемое значение, здесь это не важно
            }
        }
        table.printTable();
        fabric.printStatisticOrganism();

    }

    public void setStartupSettings() {
        statisticOrganism = new StatisticOrganism();
        getSetting();
        getTableZoo();
        getFabric();
    }

    public void getSetting() {
        settings = new SettingsEntity();
        settings.setDefaultSettings();
        settings.setSettingsFromFile();
        settings.initializationField();
    }

    public void getTableZoo() {
        table = new Table(statisticOrganism, settings);
        table.createCell();
    }

    public void getFabric() {
        fabric = new FabricOrganism(statisticOrganism, settings);
        fabric.createCopyBase();
    }


    public void startLive() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(100);
            for (int i = 0; i < table.height; i++) {
                for (int j = 0; j < table.width; j++) {
                    executorService.scheduleAtFixedRate(new OrganismWorker(table.getCurrentCell(i, j), settings), 0, 1, TimeUnit.SECONDS);
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
