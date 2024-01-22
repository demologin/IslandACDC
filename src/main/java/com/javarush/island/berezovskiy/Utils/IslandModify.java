package com.javarush.island.berezovskiy.Utils;

import com.javarush.island.berezovskiy.Configs.Configs;
import com.javarush.island.berezovskiy.Constants.Constants;
import com.javarush.island.berezovskiy.Entities.Cell.Cell;
import com.javarush.island.berezovskiy.Entities.Island;
import com.javarush.island.berezovskiy.Entities.Organism.Animals.Herbivores.*;
import com.javarush.island.berezovskiy.Entities.Organism.Animals.Predators.*;
import com.javarush.island.berezovskiy.Entities.Organism.Organism;
import com.javarush.island.berezovskiy.Entities.Organism.Flock;
import com.javarush.island.berezovskiy.Entities.Organism.Plants.Grass;
import com.javarush.island.berezovskiy.Entities.Statistics.StatisticPrinter;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public final class IslandModify {

    private static Island island;


    public static void drawIsland(Island island) {
        if (IslandModify.island == null) {
            IslandModify.island = island;
        }
        for (Cell[] cells : island.getIsland()) {
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
        ///TODO PUT IN BUILDER
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
