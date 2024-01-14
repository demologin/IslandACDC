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

    private FabricOrganism fabric;

    public void bootstrap() {

        setStartupSettings();
        for (Class<?> nameClass :
                settings.classNameOrganism) {
            int randomCounter = ThreadLocalRandom.current().nextInt(20, 500);
            if (Plant.class.isAssignableFrom(nameClass)) {
                randomCounter += settings.initialNumberOfPlants;     //чтобы хватило травоядным или тут задавать сдвиг минимума по траве
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
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(table.height * table.width);
            for (int i = 0; i < table.height; i++) {
                for (int j = 0; j < table.width; j++) {
                    scheduledExecutorService.scheduleAtFixedRate(new OrganismWorker(table.getCurrentCell(i, j), settings), 0, 1, TimeUnit.SECONDS);
                }
            }

        ExecutorService executorService = Executors.newFixedThreadPool(10);     //какая форма executer
        for (int i = 0; i < 10; i++) {
            executorService.execute(new TransferWorker(table.getTableGame()));
        }
        executorService.shutdown();


        //тут нужен выход из игры
        while(true) {
            try {
                Thread.sleep(2000);
                table.printTable();
                System.out.println();
                statisticOrganism.printStatistic();
                //из статистики кидается исключение для завершения игры?

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
