package com.javarush.island.berezovskiy.Utils;

import com.javarush.island.berezovskiy.Constants.Constants;
import com.javarush.island.berezovskiy.Entities.Cell.Cell;
import com.javarush.island.berezovskiy.Entities.Organism.Organism;
import com.javarush.island.berezovskiy.Entities.Organism.OrganismsEnum;
import com.javarush.island.berezovskiy.Entities.Organism.OrganismsSet;

import java.util.concurrent.ThreadLocalRandom;

public final class OrganismModify {

    //TODO REFACTOR!
    public static void createOrganismsOnIsland(Cell[][] islandSize) {
        int[] herbivoreCoordinates = getRandomCell(islandSize);
        int[] predatorCoordinates = getRandomCell(islandSize);

        OrganismsSet[] organismsSets = getRandomOrganismsSet();
        OrganismsSet herbivoresSet = organismsSets[0];
        OrganismsSet predatorsSet = organismsSets[1];

        islandSize[herbivoreCoordinates[0]][herbivoreCoordinates[1]].putOrganism(herbivoresSet.getOrganismType(), herbivoresSet);
        islandSize[predatorCoordinates[0]][predatorCoordinates[1]].putOrganism(predatorsSet.getOrganismType(), predatorsSet);

    }

    private static OrganismsSet[] getRandomOrganismsSet() {
        int randomHerbivore = ThreadLocalRandom.current().nextInt(0, Constants.HERBIVORES.length);
        int randomPredator = ThreadLocalRandom.current().nextInt(0, Constants.PREDATORS.length);
        int randomPlant = ThreadLocalRandom.current().nextInt(0, Constants.PLANTS.length);

        String herbivore = Constants.HERBIVORES[randomHerbivore];
        String predator = Constants.PREDATORS[randomPredator];
        String plant = Constants.PLANTS[randomPlant];

        OrganismsEnum herbivoreEnum = OrganismsEnum.valueOf(herbivore);
        OrganismsEnum predatorEnum = OrganismsEnum.valueOf(predator);
        OrganismsEnum[] organismsEnums = {};


        OrganismsSet herbivoreSet = new OrganismsSet(herbivoreEnum);
        OrganismsSet predatorSet = new OrganismsSet(predatorEnum);
        return new OrganismsSet[]{herbivoreSet, predatorSet};
    }

    private static int[] getRandomCell(Cell[][] islandSize) {
        int coordinateX = ThreadLocalRandom.current().nextInt(0, islandSize.length);
        int coordinateY = ThreadLocalRandom.current().nextInt(0, islandSize[0].length);
        return new int[]{coordinateX, coordinateY};
    }

//    private static OrganismsSet[] getRandomOrganismsSet1(int herbivores, int predators, int plants) {
//        int randomPredator = ThreadLocalRandom.current().nextInt(0, Constants.PREDATORS.length);
//        int randomPlant = ThreadLocalRandom.current().nextInt(0, Constants.PLANTS.length);
//
//        OrganismsSet[] herbivoresSet = new OrganismsSet[herbivores];
//        for (int i = 0; i < herbivoresSet.length; i++) {
//            int randomHerbivore = ThreadLocalRandom.current().nextInt(0, Constants.HERBIVORES.length);
//            String herbivore = Constants.HERBIVORES[randomHerbivore];
//            OrganismsEnum herbivoreEnum = OrganismsEnum.valueOf(herbivore);
//            herbivoresSet[i] = new OrganismsSet(herbivoreEnum);
//        }
//        OrganismsSet[] predatorSet = new OrganismsSet[predators];
//        for (int i = 0; i < predatorSet.length; i++) {
//
//        }
//        OrganismsSet[] plantsSet = new OrganismsSet[plants];
//
//        String herbivore = Constants.HERBIVORES[randomHerbivore];
//        String predator = Constants.PREDATORS[randomPredator];
//        String plant = Constants.PLANTS[randomPlant];
//
//        OrganismsEnum herbivoreEnum = OrganismsEnum.valueOf(herbivore);
//        OrganismsEnum predatorEnum = OrganismsEnum.valueOf(predator);
//        OrganismsEnum[] organismsEnums = {};
//
//
//        OrganismsSet herbivoreSet = new OrganismsSet(herbivoreEnum);
//        OrganismsSet predatorSet = new OrganismsSet(predatorEnum);
//        return new OrganismsSet[]{herbivoreSet, predatorSet};
//    }


}

