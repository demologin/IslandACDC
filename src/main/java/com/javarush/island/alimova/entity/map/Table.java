package com.javarush.island.alimova.entity.map;

import com.javarush.island.alimova.configure.SettingsEntity;
import com.javarush.island.alimova.entity.alive.Organism;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Table {

    @Getter
    public final int height;
    @Getter
    public final int width;

    public static Queue<TransferOrganism> transferAnimalQueue = new ConcurrentLinkedDeque<TransferOrganism>();

    private final SettingsEntity settings;
    @Getter
    Cell[][] tableGame;

    private final StatisticOrganism statisticOrganism;

    public static void addAnimalToTransfer(TransferOrganism transferOrganism) {
        transferAnimalQueue.add(transferOrganism);
    }

    public static TransferOrganism getAnimalFromTransferQueue() {
        return transferAnimalQueue.poll();
    }

    public Table(StatisticOrganism statisticOrganism, SettingsEntity settings) {
        this.statisticOrganism = statisticOrganism;
        this.settings = settings;
        height = settings.heightTable;
        width = settings.widthTable;
    }
    //тут может храниться класс для статистики

    public void createCell() {
        tableGame = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tableGame[i][j] = new Cell(i, j, statisticOrganism, settings);
            }
        }
    }

    //этот метод необходимо убрать, за вывод будет отвечать другой класс
    public void printTable() {
        for (int i = 0; i < settings.viewHeightTable; i++) {
            for (int j = 0; j < settings.viewWidthTable; j++) {
                System.out.print("[");
                Set<Map.Entry<Class<?>, List<Organism>>> set;
                tableGame[i][j].getLocker().lock();
                try {
                    set = tableGame[i][j].getEntry();
                } finally {
                    tableGame[i][j].getLocker().unlock();
                }
                if (Objects.nonNull(set)) {
                    for (Map.Entry<Class<?>, List<Organism>> value : set) {
                        int sizeList = value.getValue().size();
                        if (sizeList != 0) {
                            int indexIcon = settings.getIndexOrganism(value.getKey());
                            System.out.print(settings.iconOrganism[indexIcon] + " - " + sizeList + " ");
                        }

                    }
                }


                System.out.print("]");
            }
            System.out.println();
        }
    }

    public void printQueueTransfer() {
        System.out.println("\n[ ");
        System.out.println(Arrays.toString(transferAnimalQueue.toArray()));
        System.out.println("]");
    }

    public boolean addNewOrganism(int x, int y, Organism organism) {
        Class<?> classOrganism = organism.getClass();
        if (tableGame[x][y].checkLimitOrganism(classOrganism.getSimpleName())) {
            tableGame[x][y].addOrganismToQueueWithStatistic(organism);
            tableGame[x][y].addOrganismsFromQueue();
            return true;
        } else {
            return false;
        }
    }

    public Cell getCurrentCell(int x, int y) {
        return tableGame[x][y];
    }
}
