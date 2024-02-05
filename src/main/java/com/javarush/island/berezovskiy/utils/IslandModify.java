package com.javarush.island.berezovskiy.utils;

import com.javarush.island.berezovskiy.configs.Configs;
import com.javarush.island.berezovskiy.constants.Constants;
import com.javarush.island.berezovskiy.entities.cell.Cell;
import com.javarush.island.berezovskiy.entities.Island;
import com.javarush.island.berezovskiy.entities.organism.Flock;
import com.javarush.island.berezovskiy.printer.StatisticPrinter;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public final class IslandModify {

    private IslandModify() {
    }

    private static Island island;


    public static void drawIsland(Island island) {
        if (IslandModify.island == null) {
            IslandModify.island = island;
        }
        System.out.println();
        for (Cell[] cells : island.getIslandField()) {
            for (Cell cell : cells) {
                printDelimiter("|");
                if (!cell.getOrganismHashMap().isEmpty()) {
                    ConcurrentMap<String, Flock> flockMap = cell.getOrganismHashMap();
                    int flocksAmountInCell = flockMap.size();
                    int count = Configs.ANIMAL_LIMIT_IN_CELL;
                    for (Map.Entry<String, Flock> stringFlockEntry : flockMap.entrySet()) {
                        Flock flock = stringFlockEntry.getValue();
                        printOrganismImage(flock);
                        flocksAmountInCell--;
                        count--;
                        printEmptyImage(flocksAmountInCell, count);
                    }
                } else {
                    for (int i = 0; i < Configs.ANIMAL_LIMIT_IN_CELL; i++) {
                        System.out.print(Constants.EMPTY);
                    }
                }
                printDelimiter("| ");
            }
            System.out.println();
            System.out.println();
        }
        StatisticPrinter.showStatistic(island);
    }

    private static void printDelimiter(String s) {
        System.out.print(s);
    }

    private static void printEmptyImage(int flocksAmountInCell, int count) {
        if (flocksAmountInCell == 0) {
            for (int i = 0; i < count; i++) {
                System.out.print(Constants.EMPTY);
            }
        }
    }

    private static void printOrganismImage(Flock flock) {
        String organismName = flock.getOrganism().getName();
        String organismImage = Constants.IMAGE_MAP.get(organismName);
        System.out.print(organismImage);
    }

    public static Island getCurrentIsland() {
        return island;
    }
}
