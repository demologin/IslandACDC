package com.javarush.island.khasanov.service;

import com.javarush.island.khasanov.config.Setting;
import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.entity.plants.Grass;
import com.javarush.island.khasanov.entity.Position;
import com.javarush.island.khasanov.util.Rndm;

import java.util.Set;

public class GrowingService implements Runnable {
    private final Island island;

    public GrowingService(Island island) {
        this.island = island;
    }
    @Override
    public void run() {
        if (Rndm.choose(0, 1) == 1) {
            for (int i = 0; i < Setting.countPlantsForGrowing; i++) {
                Position position = Rndm.nextPosition();
                Set<IslandObject> islandObjects = island.getIslandMap().get(position);
                IslandObject grass = new Grass(island, position);
                if (island.isEnoughSpace(position, grass)) {
                    islandObjects.add(grass);
                    island.incrementObjectOnField(grass);
                }
            }
        }
    }
}
