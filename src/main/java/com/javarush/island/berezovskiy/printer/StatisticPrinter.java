package com.javarush.island.berezovskiy.printer;

import com.javarush.island.berezovskiy.constants.Constants;
import com.javarush.island.berezovskiy.entities.cell.Cell;
import com.javarush.island.berezovskiy.entities.Island;
import com.javarush.island.berezovskiy.entities.organism.animals.herbivores.*;
import com.javarush.island.berezovskiy.entities.organism.animals.predators.*;
import com.javarush.island.berezovskiy.entities.organism.Flock;
import com.javarush.island.berezovskiy.entities.organism.Organism;
import com.javarush.island.berezovskiy.entities.organism.OrganismsEnum;
import com.javarush.island.berezovskiy.entities.organism.plants.Grass;

import java.util.*;

public final class StatisticPrinter {
    private static final HashMap<String, Organism> organisms = new HashMap<>();

    private StatisticPrinter() {
    }

    public static void showStatistic(Island island) {
        getOrganismsOnIsland(island);
        printTotalOrganismsAmountOnIsland();
        printOrganismsAmountOnIsland();
    }

    private static void printOrganismsAmountOnIsland() {
        for (Map.Entry<String, Organism> organismEntry : organisms.entrySet()) {
            String organismName = organismEntry.getKey();
            int organismAmount = defineOrganismType(organismName);
            String organismImage = Constants.IMAGE_MAP.get(organismName);
            if (organismAmount > 0) {
                System.out.print(organismImage + " = " + organismAmount + " ");
            }
        }
    }

    private static void printTotalOrganismsAmountOnIsland() {
        System.out.println(Constants.TOTAL_ORGANISM_AMOUNT + Organism.getOrganismNumber());
    }

    private static int defineOrganismType(String name) {
        OrganismsEnum organismsEnum = OrganismsEnum.valueOf(name);
        return switch (organismsEnum) {
            case WOLF -> Wolf.getOrganismNumber().get();
            case BOA -> Boa.getOrganismNumber().get();
            case FOX -> Fox.getOrganismNumber().get();
            case BEAR -> Bear.getOrganismNumber().get();
            case EAGLE -> Eagle.getOrganismNumber().get();
            case HORSE -> Horse.getOrganismNumber().get();
            case DEER -> Deer.getOrganismNumber().get();
            case RABBIT -> Rabbit.getOrganismNumber().get();
            case MOUSE -> Mouse.getOrganismNumber().get();
            case GOAT -> Goat.getOrganismNumber().get();
            case SHEEP -> Sheep.getOrganismNumber().get();
            case BOAR -> Boar.getOrganismNumber().get();
            case BUFFALO -> Buffalo.getOrganismNumber().get();
            case DUCK -> Duck.getOrganismNumber().get();
            case CATERPILLAR -> Caterpillar.getOrganismNumber().get();
            case GRASS -> Grass.getOrganismNumber().get();
        };
    }

    private static void getOrganismsOnIsland(Island island) {
        for (Cell[] cells : island.getIslandField()) {
            for (Cell cell : cells) {
                for (Map.Entry<String, Flock> stringFlockEntry : cell.getOrganismHashMap().entrySet()) {
                    String organismName = stringFlockEntry.getValue().getOrganism().getName();
                    Organism organism = stringFlockEntry.getValue().getOrganism();
                    organisms.put(organismName, organism);
                }
            }
        }
    }
}
