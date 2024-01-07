package com.javarush.island.alimova.entity.map;

import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.services.StatisticOrganism;

import java.util.Arrays;

public class Table {

    Cell[][] tableGame = new Cell[10][10];
    private final StatisticOrganism statisticOrganism;
    //тут может храниться класс для статистики
    {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tableGame[i][j] = new Cell();
            }
        }
    }

    public Table(StatisticOrganism statisticOrganism) {
        this.statisticOrganism = statisticOrganism;
    }

    //этот метод необходимо убрать, за вывод будет отвечать другой класс
    public void printTable() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(Arrays.toString(tableGame[i][j].getSetCreatures().toArray()));
            }
            System.out.println();
        }
    }

    public void addNewOrganism(int x, int y, Organism organism) {
        tableGame[x][y].addOrganism(organism);
    }
}
