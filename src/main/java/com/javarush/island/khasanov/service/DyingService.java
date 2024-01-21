package com.javarush.island.khasanov.service;

import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.repository.ActionsQueue;
import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.repository.Position;

import java.util.Map;
import java.util.Set;

public class DyingService implements Runnable {
    private final Island island;

    public DyingService(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        fillDyingQueue();
        serveQueue();
    }

    private void fillDyingQueue() {
        for (Map.Entry<Position, Set<IslandObject>> entry : island.getIslandMap().entrySet()) {
            for (IslandObject islandObject : entry.getValue()) {
                islandObject.strave();
                if (!islandObject.getIsAlive().get()) {
                    ActionsQueue.dying.add(islandObject);
                }
            }
        }
    }
    private void serveQueue() {
        ActionsQueue.dying.forEach(island::dieIslandObject);
    }
}
