package com.javarush.island.alimova.services;

import com.javarush.island.alimova.configure.DefaultSettings;
import com.javarush.island.alimova.configure.SettingsEntity;
import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.entity.alive.plants.Plant;
import com.javarush.island.alimova.entity.map.StatisticOrganism;
import com.javarush.island.alimova.entity.map.Table;
import com.javarush.island.alimova.exception.GameException;

import java.util.concurrent.*;

public class ManagerZoo {

    private StatisticOrganism statisticOrganism;
    private SettingsEntity settings;
    private Table table;

    private ScheduledExecutorService executorServiceOrganism;

    private ScheduledExecutorService executorServiceTransfer;

    private FabricOrganism fabric;

    public void bootstrap() {

        setStartupSettings();
        for (Class<?> nameClass :
                settings.classNameOrganism) {
            int randomCounter = ThreadLocalRandom.current().nextInt(settings.minRandomOrganism, settings.maxRandomOrganism + 1);
            if (Plant.class.isAssignableFrom(nameClass)) {
                randomCounter += settings.initialNumberOfPlants;
            }
            for (int i = 0; i < randomCounter; i++) {
                Organism organism = fabric.createNewInstanceOrganism(nameClass);
                int indexX = ThreadLocalRandom.current().nextInt(0, table.height);
                int indexY = ThreadLocalRandom.current().nextInt(0, table.width);
                table.addNewOrganism(indexX, indexY, organism);
            }
        }
        try {
            table.printTable();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new GameException(DefaultSettings.MESSAGE_INDEX_OUTPUT, e);
        }
        statisticOrganism.printStatistic();

    }

    public void setStartupSettings() {
        getSetting();
        statisticOrganism = new StatisticOrganism(settings);
        statisticOrganism.initStatistic();
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
        fabric = new FabricOrganism(settings);
        fabric.createCopyBase();
    }

    public void startLive() {

        executorServiceOrganism = Executors.newSingleThreadScheduledExecutor();
        executorServiceOrganism.scheduleAtFixedRate(this::testWork, 0, settings.periodGame, TimeUnit.SECONDS);

        executorServiceTransfer = Executors.newSingleThreadScheduledExecutor();
        executorServiceTransfer.scheduleWithFixedDelay(new TransferWorker(table.getTableGame()), 0, 10, TimeUnit.MILLISECONDS);

    }

    private void testWork() {
        ExecutorService executorService = Executors.newFixedThreadPool(table.height * table.width);
        for (int i = 0; i < table.height; i++) {
            for (int j = 0; j < table.width; j++) {
                executorService.execute((new OrganismWorker(table.getCurrentCell(i, j), settings)));
            }
        }
        executorService.shutdown();

        try {
            if(executorService.awaitTermination(settings.periodGame, TimeUnit.SECONDS)) {
                table.printTable();
                System.out.println();
                statisticOrganism.printStatistic();
                if (!statisticOrganism.checkAliveAnimals()) {
                    System.out.println(DefaultSettings.MESSAGE_FINAL_GAME);
                    stopGame();
                    statisticOrganism.printStatistic();
                }
            } else {
                System.err.println(DefaultSettings.MESSAGE_TIME_OUT_GAME);
                stopGame();

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(DefaultSettings.MESSAGE_THREAD_INTERRUPTED, e);
        }
    }

    private void stopGame() {
        executorServiceOrganism.shutdown();
        executorServiceTransfer.shutdown();
    }
}