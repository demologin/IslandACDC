package com.javarush.island.alimova.services;

import com.javarush.island.alimova.configure.SettingsEntity;
import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.entity.alive.plants.Plant;
import com.javarush.island.alimova.entity.map.StatisticOrganism;
import com.javarush.island.alimova.entity.map.Table;

import java.util.concurrent.*;

public class ManagerZoo {

    private StatisticOrganism statisticOrganism;
    private SettingsEntity settings;
    private Table table;

    private final int AMOUNT_THREAD_GAME = 1;

    private final int AMOUNT_THREAD_TRANSFER = 10;

    private FabricOrganism fabric;

    public void bootstrap() {

        setStartupSettings();
        for (Class<?> nameClass :
                settings.classNameOrganism) {
            int randomCounter = ThreadLocalRandom.current().nextInt(settings.minRandomOrganism, settings.maxRandomOrganism);
            if (Plant.class.isAssignableFrom(nameClass)) {
                randomCounter += settings.initialNumberOfPlants;
            }
            for (int i = 0; i < randomCounter; i++) {
                Organism organism = fabric.createNewInstanceOrganism(nameClass);
                int indexX = ThreadLocalRandom.current().nextInt(0, table.height);
                int indexY = ThreadLocalRandom.current().nextInt(0, table.width);
                table.addNewOrganism(indexX, indexY, organism);     //тут игнорируем возвращаемое значение, здесь это не важно
            }
        }
        table.printTable();
        fabric.printStatisticOrganism();

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
        fabric = new FabricOrganism(statisticOrganism, settings);
        fabric.createCopyBase();
    }

    public void startLive() {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(AMOUNT_THREAD_GAME);
        scheduledExecutorService.scheduleAtFixedRate(new GameWorker(table, settings, statisticOrganism), 0, settings.periodGame, TimeUnit.SECONDS);
        startTransferCell();
    }

    private void startTransferCell() {
        ExecutorService executorService = Executors.newFixedThreadPool(AMOUNT_THREAD_TRANSFER);
        for (int i = 0; i < AMOUNT_THREAD_TRANSFER; i++) {
            executorService.execute(new TransferWorker(table.getTableGame()));
        }
        executorService.shutdown();

    }
}
