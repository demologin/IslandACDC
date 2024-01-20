package com.javarush.island.berezovskiy.Utils;

import com.javarush.island.berezovskiy.Constants.Constants;
import com.javarush.island.berezovskiy.Entities.Cell.Cell;
import com.javarush.island.berezovskiy.Entities.Organism.OrganismsEnum;
import com.javarush.island.berezovskiy.Entities.Organism.Flock;

import java.util.concurrent.ThreadLocalRandom;

public final class OrganismModify {

    //TODO REFACTOR!
    public static void createOrganismsOnIsland(Cell[][] islandSize) {
        int[] herbivoreCoordinates = getRandomCell(islandSize);
        int[] predatorCoordinates = getRandomCell(islandSize);

        Flock[] flocks = getRandomOrganismsSet();
        Flock herbivoresSet = flocks[0];
        Flock predatorsSet = flocks[1];

        islandSize[herbivoreCoordinates[0]][herbivoreCoordinates[1]].putOrganism(herbivoresSet.getOrganismType(), herbivoresSet);
        islandSize[predatorCoordinates[0]][predatorCoordinates[1]].putOrganism(predatorsSet.getOrganismType(), predatorsSet);

    }

    private static Flock[] getRandomOrganismsSet() {
        int randomHerbivore = ThreadLocalRandom.current().nextInt(0, Constants.HERBIVORES.length);
        int randomPredator = ThreadLocalRandom.current().nextInt(0, Constants.PREDATORS.length);
        int randomPlant = ThreadLocalRandom.current().nextInt(0, Constants.PLANTS.length);

        String herbivore = Constants.HERBIVORES[randomHerbivore];
        String predator = Constants.PREDATORS[randomPredator];
        String plant = Constants.PLANTS[randomPlant];

        OrganismsEnum herbivoreEnum = OrganismsEnum.valueOf(herbivore);
        OrganismsEnum predatorEnum = OrganismsEnum.valueOf(predator);
        OrganismsEnum[] organismsEnums = {};


        Flock herbivoreSet = new Flock(herbivoreEnum);
        Flock predatorSet = new Flock(predatorEnum);
        return new Flock[]{herbivoreSet, predatorSet};
    }

    private static int[] getRandomCell(Cell[][] islandSize) {
        int coordinateX = ThreadLocalRandom.current().nextInt(0, islandSize.length);
        int coordinateY = ThreadLocalRandom.current().nextInt(0, islandSize[0].length);
        return new int[]{coordinateX, coordinateY};
    }
}

