package com.javarush.island.berezovskiy.Utils;

import com.javarush.island.berezovskiy.Constants.Constants;
import com.javarush.island.berezovskiy.Entities.Cell.Cell;
import com.javarush.island.berezovskiy.Entities.Island;
import com.javarush.island.berezovskiy.Entities.Organism.Animals.Herbivores.Rabbit;
import com.javarush.island.berezovskiy.Entities.Organism.Animals.Predators.Wolf;
import com.javarush.island.berezovskiy.Entities.Organism.Organism;
import com.javarush.island.berezovskiy.Entities.Organism.OrganismsSet;

import java.util.HashMap;
import java.util.Map;

public final class IslandModify {


    //TODO REFACTOR
    public static void drawIsland(Island island) {
        Cell[][] cellsArray = island.getIsland();
        for (Cell[] cells : cellsArray) {
            for (Cell cell : cells) {
                System.out.print("|");
                if (!cell.getOrganismHashMap().isEmpty()) {
                    HashMap<String, OrganismsSet> hashMap = cell.getOrganismHashMap();
                    for (Map.Entry<String, OrganismsSet> organismEntry : hashMap.entrySet()) {
                        OrganismsSet organismsSet = organismEntry.getValue();
                        String organismName = organismsSet.getOrganism().getName();
                        int count = 4;
                        if (organismName.equals(Constants.WOLF)) {
                            System.out.print(Constants.WOLF_IMAGE);
                            count--;
                        }
                        if (organismName.equals(Constants.RABBIT)) {
                            System.out.print(Constants.RABBIT_IMAGE);
                            count--;
                        }
                        ///TODO Invalid count
                        if(hashMap.size()){
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
}
