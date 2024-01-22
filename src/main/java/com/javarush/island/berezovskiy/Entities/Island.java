package com.javarush.island.berezovskiy.Entities;

import com.javarush.island.berezovskiy.Configs.Configs;
import com.javarush.island.berezovskiy.Entities.Cell.Cell;
import com.javarush.island.berezovskiy.Entities.Organism.Organism;
import com.javarush.island.berezovskiy.Utils.IslandModify;
import com.javarush.island.berezovskiy.Utils.OrganismModify;

public class Island implements Runnable {

    private final int sizeY = Configs.ISLAND_WIDTH;
    private final int sizeX = Configs.ISLAND_HEIGHT;

    private final Cell[][] island;
    private boolean islandNotCreated = true;

    public Cell[][] getIsland() {
        return island;
    }

    public Island() {
        island = new Cell[sizeX][sizeY];
        createIslandField(island);
        OrganismModify.createOrganismsOnIsland(island);
    }
    @Override
    public void run() {
        while(Organism.getOrganismNumber().get()>0) {
            IslandModify.drawIsland(this);
            System.out.println();
            try {
                Thread.sleep(Configs.TIME_FOR_WAITING*1000);
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
