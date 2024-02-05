package com.javarush.island.berezovskiy.entities;

import com.javarush.island.berezovskiy.configs.Configs;
import com.javarush.island.berezovskiy.entities.cell.Cell;
import com.javarush.island.berezovskiy.entities.organism.Organism;
import com.javarush.island.berezovskiy.utils.IslandModify;
import com.javarush.island.berezovskiy.utils.OrganismModify;

public class Island implements Runnable {

    private final Cell[][] islandField;
    private boolean islandNotCreated = true;

    public Cell[][] getIslandField() {
        return islandField;
    }

    public Island() {
        int islandWidthY = Configs.ISLAND_WIDTH;
        int islandHeightX = Configs.ISLAND_HEIGHT;
        islandField = new Cell[islandHeightX][islandWidthY];
        createIslandField(islandField);
        OrganismModify.createOrganismsOnIsland(islandField);
    }

    @Override
    public void run() {
        while (Organism.getOrganismNumber().get() > 0) {
            IslandModify.drawIsland(this);
            System.out.println();
            try {
                Thread.sleep(Configs.TIME_FOR_WAITING * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void createIslandField(Cell[][] islandSize) {
        if (islandNotCreated) {
            for (int coordinateX = 0; coordinateX < islandSize.length; coordinateX++) {
                for (int coordinateY = 0; coordinateY < islandSize[coordinateX].length; coordinateY++) {
                    islandSize[coordinateX][coordinateY] = new Cell(coordinateX, coordinateY);
                }
            }
            islandNotCreated = false;
        }
    }
}
