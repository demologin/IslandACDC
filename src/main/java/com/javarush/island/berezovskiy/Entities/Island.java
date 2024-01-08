package com.javarush.island.berezovskiy.Entities;

import com.javarush.island.berezovskiy.Configs.Configs;
import com.javarush.island.berezovskiy.Constants.Constants;
import com.javarush.island.berezovskiy.Entities.Cell.Cell;
import com.javarush.island.berezovskiy.Entities.Organism.Animals.Herbivores.Rabbit;
import com.javarush.island.berezovskiy.Entities.Organism.Animals.Predators.Wolf;
import com.javarush.island.berezovskiy.Entities.Organism.Organism;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

public class Island {

    private static final int sizeY = Configs.ISLAND_SIZE_Y;
    private static final int sizeX = Configs.ISLAND_SIZE_X;
    private final Cell[][] islandSize;

    public Island() {
        islandSize = new Cell[sizeY][sizeX];
        createIslandField(islandSize);
        createOrganisms(islandSize);
        drawIsland();
    }


    //TODO REFACTOR!!!
    private void drawIsland() {
        for (Cell[] cells : islandSize) {
            for (Cell cell : cells) {
                if(!cell.getOrganismIntegerHashMap().isEmpty()){
                    HashMap<Organism, Integer> hashMap = cell.getOrganismIntegerHashMap();
                    for (Map.Entry<Organism, Integer> organismIntegerEntry : hashMap.entrySet()) {
                        Organism organism = organismIntegerEntry.getKey();
                        Class<? extends Organism> organismClass = organism.getClass();
                        if(organismClass.getSimpleName().equals("Wolf")){
                            System.out.print(Constants.WOLF_IMAGE + " ");
                        }
                        if(organismClass.getSimpleName().equals("Rabbit")){
                            System.out.print(Constants.RABBIT_IMAGE + " ");
                        }
                    }
                }
                else{
                    System.out.print(Constants.EMPTY + " ");
                }
            }
            System.out.println();
        }
    }


    //TODO - REFACTOR!!!
    private void createOrganisms(Cell[][] islandSize) {
        int wolfsCount = 30;
        int rabbitsCount = 100;
        int cellForWolfsX = ThreadLocalRandom.current().nextInt(0,islandSize.length);
        int cellForWolfsY = ThreadLocalRandom.current().nextInt(0,islandSize.length);
        int cellForRabbitsX = ThreadLocalRandom.current().nextInt(0,islandSize.length);
        int cellForRabbitsY = ThreadLocalRandom.current().nextInt(0,islandSize.length);

        islandSize[cellForWolfsY][cellForWolfsX].putOrganism(new Wolf(), wolfsCount);
        islandSize[cellForRabbitsY][cellForRabbitsX].putOrganism(new Rabbit(), rabbitsCount);

    }

    private void createIslandField(Cell[][] islandSize) {
        for (int coordinateX = 0; coordinateX < islandSize.length; coordinateX++) {
            for (int coordinateY = 0; coordinateY < islandSize[coordinateX].length; coordinateY++) {
                islandSize[coordinateX][coordinateY] = new Cell(coordinateX,coordinateY);
            }
        }
    }
}
