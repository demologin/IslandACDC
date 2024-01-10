package com.javarush.island.alimova.entity.map;

import com.javarush.island.alimova.configure.SettingsEntity;
import com.javarush.island.alimova.entity.alive.Organism;

public class Table {

    public final int height = 200;
    public final int width = 10;


    private final SettingsEntity settings;
    Cell[][] tableGame = new Cell[height][width];

    private final StatisticOrganism statisticOrganism;

    public Table(StatisticOrganism statisticOrganism, SettingsEntity settings) {
        this.statisticOrganism = statisticOrganism;
        this.settings = settings;
    }
    //тут может храниться класс для статистики

    public void createCell() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tableGame[i][j] = new Cell(statisticOrganism, settings);
            }
        }
    }

    //этот метод необходимо убрать, за вывод будет отвечать другой класс
    public void printTable() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print("[");
                long[] list = tableGame[i][j].getListAmountOrganism();
//                for (int k = 0; k < list.length; k++) {
//                    if (list[k] != 0) {
//                        System.out.print(" " + settings.nameOrganism[k] + " - " + list[k]);
//                    }
//                }
                //пока для тестирования обрабатываем траву
                if (list[15] != 0) {
                    System.out.print(" " + settings.nameOrganism[15] + " - " + list[15]);
                }
                System.out.print("]");
            }
            System.out.println();
        }
    }

    public boolean addNewOrganism(int x, int y, Organism organism) {
        Class<?> classOrganism = organism.getClass();
        if (tableGame[x][y].checkLimitOrganism(classOrganism.getSimpleName())) {
            tableGame[x][y].addOrganismToQueue(organism);
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
