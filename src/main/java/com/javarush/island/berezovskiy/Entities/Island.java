package com.javarush.island.berezovskiy.Entities;

import com.javarush.island.berezovskiy.Configs.Configs;
import com.javarush.island.berezovskiy.Entities.Cell.Cell;
import com.javarush.island.berezovskiy.Entities.Organism.Organism;
import com.javarush.island.berezovskiy.Utils.IslandModify;
import com.javarush.island.berezovskiy.Utils.OrganismModify;

import java.util.concurrent.TimeUnit;

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
        IslandModify.drawIsland(this);
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

//    private void extracted() {
//        for (Cell[] cells : island) {
//            for (Cell cell : cells) {
//                for (Map.Entry<String, OrganismsSet> organismIntegerEntry : cell.getOrganismHashMap().entrySet()) {
//                    OrganismsSet organismsSet = organismIntegerEntry.getValue();
//                    Organism organism = organismsSet.getOrganism();
//                    if (organism.getClass() == Rabbit.class) {
//                        organismsSet.move();
//                        if (!island[organismsSet.getCoordinateX()][organismsSet.getCoordinateY()].getOrganismHashMap().containsKey("RABBIT")) {
//                            island[organismsSet.getCoordinateX()][organismsSet.getCoordinateY()].putOrganism("RABBIT", organismsSet);
//                            cell.removeOrganism("RABBIT");
//                            System.out.println("Rabbit X = " + organismsSet.getCoordinateY() + " Y = " + organismsSet.getCoordinateX());
//                        }
//                        System.out.println("Total Rabbit Count = " + organismsSet.getOrganismCount());
//                    }
//                    if (organism.getClass() == Wolf.class) {
//                        organismsSet.move();
//                        if (!island[organismsSet.getCoordinateX()][organismsSet.getCoordinateY()].getOrganismHashMap().containsKey("WOLF")) {
//                            island[organismsSet.getCoordinateX()][organismsSet.getCoordinateY()].putOrganism("WOLF", organismsSet);
//                            cell.removeOrganism("WOLF");
//                        }
//                        System.out.println("Total Wolf Count = " + organismsSet.getOrganismCount());
//                    }
//                }
//            }
//        }
//    }

//    private void changeMap(){
//        for ()
//    }
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
