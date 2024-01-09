package com.javarush.island.alimova.entity.map;

import com.javarush.island.alimova.configure.DefaultSettings;
import com.javarush.island.alimova.entity.alive.Organism;

public class Table {

    public final int width = 10;
    public final int height = 10;
    Cell[][] tableGame = new Cell[height][width];

    private final StatisticOrganism statisticOrganism;

    public Table(StatisticOrganism statisticOrganism) {
        this.statisticOrganism = statisticOrganism;
    }
    //тут может храниться класс для статистики

    public void createCell() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tableGame[i][j] = new Cell(statisticOrganism);
            }
        }
    }

    //этот метод необходимо убрать, за вывод будет отвечать другой класс
    public void printTable() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print("[");
                long[] list = tableGame[i][j].getListAmountOrganism();
                for (int k = 0; k < list.length; k++) {
                    if (list[k] != 0) {
                        System.out.print(" " + DefaultSettings.nameOrganism[k] + " - " + list[k]);
                    }
                }
                System.out.print("]");
            }
            System.out.println();
        }
    }

    public void addNewOrganism(int x, int y, Organism organism) {
        tableGame[x][y].addOrganismToQueue(organism);
    }

    public Cell getCurrentCell(int x, int y) {
        return tableGame[x][y];
    }
}
