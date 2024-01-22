package com.javarush.island.berezovskiy.Utils;

import com.javarush.island.berezovskiy.Configs.Configs;
import com.javarush.island.berezovskiy.Constants.Constants;
import com.javarush.island.berezovskiy.Entities.Cell.Cell;
import com.javarush.island.berezovskiy.Entities.Organism.OrganismTypeEnum;
import com.javarush.island.berezovskiy.Entities.Organism.OrganismsEnum;
import com.javarush.island.berezovskiy.Entities.Organism.Flock;

import java.util.Map;

public final class OrganismModify {
    public static void createOrganismsOnIsland(Cell[][] island) {
        Flock[] predators = getRandomOrganismsSet(OrganismTypeEnum.PREDATOR);
        Flock[] herbivores = getRandomOrganismsSet(OrganismTypeEnum.HERBIVORE);
        Flock[] plants = getRandomOrganismsSet(OrganismTypeEnum.PLANT);

        putFlocksInCell(predators, island);
        putFlocksInCell(herbivores, island);
        putFlocksInCell(plants, island);
    }

    private static Flock[] getRandomOrganismsSet(OrganismTypeEnum organismType) {
        int organismTypeAmount = Configs.ORGANISMS_COUNT_IN_START.get(organismType.toString());
        Map<Integer, String> organismTypeMap = Constants.ORGANISM_TYPE_MAP.get(organismType);
        Flock[] flocks = new Flock[organismTypeAmount];
        for (int i = 0; i < organismTypeAmount; i++) {
            int randomOrganism = Rnd.getRandom(0, organismTypeMap.size());
            String nameRandomOrganism = organismTypeMap.get(randomOrganism);
            flocks[i] = new Flock(OrganismsEnum.valueOf(nameRandomOrganism));
        }
        return flocks;
    }
    private static int[] getRandomCell(Cell[][] islandSize) {
        int coordinateX = Rnd.getRandom(0, islandSize.length);
        int coordinateY = Rnd.getRandom(0, islandSize[0].length);
        return new int[]{coordinateX, coordinateY};
    }
    private static void putFlocksInCell(Flock[] flocks, Cell[][] island) {
        for (int i = 0; i < flocks.length; i++) {
            int[] coordinates = getRandomCell(island);
            int coordinateX = coordinates[0];
            int coordinateY = coordinates[1];
            if (!island[coordinateX][coordinateY].isCellFull()) {
                String organismName = flocks[i].getOrganism().getName();
                island[coordinateX][coordinateY].putOrganism(organismName, flocks[i]);
            } else {
                i--;
            }
        }
    }
}

