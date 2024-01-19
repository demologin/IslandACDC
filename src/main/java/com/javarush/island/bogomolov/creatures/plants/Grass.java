package com.javarush.island.bogomolov.creatures.plants;

import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.creatures.Organism;
import com.javarush.island.bogomolov.storage.Cell;
import com.javarush.island.bogomolov.storage.IslandMap;


public class Grass extends Plant {
    public Grass() {
        super("Grass", 1, 200);
    }


    @Override
    public void spawn() {

        Cell[][] map = IslandMap.getislandMap().getMap();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (IslandMap.getislandMap().getCell(i, j).getPlants().size() < 200) {
                    Cell cell = IslandMap.getislandMap().getCell(i, j);
                    IslandMap.getislandMap().addPlant(new Grass(), cell.getRow(), cell.getColumn());
                }
            }
        }


    }

    @Override
    public void spawn(Animal couple) {

    }
}
