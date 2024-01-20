package com.javarush.island.berezovskiy.Utils;

import com.javarush.island.berezovskiy.Configs.Configs;
import com.javarush.island.berezovskiy.Constants.Constants;
import com.javarush.island.berezovskiy.Entities.Cell.Cell;
import com.javarush.island.berezovskiy.Entities.Island;
import com.javarush.island.berezovskiy.Entities.Organism.Animals.Herbivores.Rabbit;
import com.javarush.island.berezovskiy.Entities.Organism.Animals.Predators.Wolf;
import com.javarush.island.berezovskiy.Entities.Organism.Organism;
import com.javarush.island.berezovskiy.Entities.Organism.Flock;

import java.util.HashMap;
import java.util.Map;

public final class IslandModify {

private static Island island;


    //TODO REFACTOR
    public static void drawIsland(Island island) {
        if(IslandModify.island == null){
            IslandModify.island = island;
        }
        Cell[][] cellsArray = island.getIsland();
        for (Cell[] cells : cellsArray) {
            for (Cell cell : cells) {
                System.out.print("|");
                if (!cell.getOrganismHashMap().isEmpty()) {
                    HashMap<String, Flock> hashMap = cell.getOrganismHashMap();
                    int hashmapSize = hashMap.size();
                    int count = Configs.ANIMAL_LIMIT_IN_CELL;
                    for (Map.Entry<String, Flock> organismEntry : hashMap.entrySet()) {
                        Flock flock = organismEntry.getValue();
                        String organismName = flock.getOrganism().getName();
                        if (organismName.equals(Constants.WOLF)) {
                            System.out.print(Constants.WOLF_IMAGE);
                            hashmapSize--;
                            count--;
                        }
                        if (organismName.equals(Constants.RABBIT)) {
                            System.out.print(Constants.RABBIT_IMAGE);
                            hashmapSize--;
                            count--;
                        }
                        if(hashmapSize == 0){
                            for (int i = 0; i < count; i++) {
                                System.out.print(Constants.EMPTY);
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < 4; i++) {
                        System.out.print(Constants.EMPTY);
                    }
                }
                System.out.print("| ");
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Organism Count = " + Organism.getOrganismNumber());
        System.out.println(Constants.WOLF_IMAGE + " Count = " + Wolf.getWolfNumber());
        System.out.println(Constants.RABBIT_IMAGE + " Count = " + Rabbit.getRabbitNumber());
    }

    public static Island getCurrentIsland(){
        return island;
    }
}
