package com.javarush.island.alimova.services;

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
        for (Class<?> nameClass :
                settings.classNameOrganism) {
            int randomCounter = ThreadLocalRandom.current().nextInt(2, 10);
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


    //Для многопоточки
    public void startLive() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
            for (int i = 0; i < table.height; i++) {
                for (int j = 0; j < table.width; j++) {
                    executorService.scheduleAtFixedRate(new OrganismWorker(table.getCurrentCell(i, j), settings), 0, 1, TimeUnit.SECONDS);
                }
            }


        while(true) {
            try {
                Thread.sleep(2000);

                System.out.println("\n");
                table.printTable();
                System.out.println();
                fabric.printStatisticOrganism();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    //ДЛЯ ТЕСТИРОВАНИЯ БЕЗ МНОГОПОТОЧКИ
    public void startTest() {

        OrganismWorker[][] workerTable = new OrganismWorker[table.getHeight()][table.getWidth()];
        //TransferWorker[] transferTable = new TransferWorker[10];
        for (int i = 0; i < table.height; i++) {
            for (int j = 0; j < table.width; j++) {
                workerTable[i][j] = new OrganismWorker(table.getCurrentCell(i, j), settings);
            }
        }

        while(true) {
            table.printQueueTransfer();
            for (int i = 0; i < table.height; i++) {
                for (int j = 0; j < table.width; j++) {
                    System.out.print("[" + i + "," + j + "] ");
                    workerTable[i][j].run();
                }
            }

            for (int i = 0; i < 10; i++) {
                TransferWorker transfer = new TransferWorker(table.getTableGame());
                transfer.run();
            }

            System.out.println("\n");
            table.printTable();
            System.out.println();

            System.out.println();
            statisticOrganism.printStatistic();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
