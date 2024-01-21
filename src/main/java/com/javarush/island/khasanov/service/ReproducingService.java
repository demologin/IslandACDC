package com.javarush.island.khasanov.service;

import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.repository.ActionsQueue;
import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.repository.Position;

import java.util.Map;
import java.util.Set;

public class ReproducingService implements Runnable {

    private final Island island;

    public ReproducingService(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        fillReproducingQueue();
        serveQueue();
    }

    private void fillReproducingQueue() {
        for (Map.Entry<Position, Set<IslandObject>> entry : island.getIslandMap().entrySet()) {
            for (IslandObject islandObject : entry.getValue()) {
                IslandObject born = islandObject.reproduce();
                boolean isNewObject = !islandObject.equals(born);

                if (isNewObject) {
                    ActionsQueue.reproduction.add(born);
                }
            }
        }
    }

    private void serveQueue() {
        ActionsQueue.reproduction.forEach(island::reproduceIslandObject);
    }
}
