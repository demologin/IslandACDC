package com.javarush.island.khasanov.service;

import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.entity.Position;
import com.javarush.island.khasanov.repository.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EatingService implements Runnable {
    private final Island island;

    public EatingService(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        fillEatingQueue();
        serveQueue();
    }

    private void fillEatingQueue() {
        for (Map.Entry<Position, Set<IslandObject>> entry : island.getIslandMap().entrySet()) {
            for (IslandObject islandObject : entry.getValue()) {
                List<IslandObject> foodList = islandObject.eat();
                if (!foodList.isEmpty()) {
                    ActionsQueue.eating.add(new HashMap<>() {{
                        put(islandObject, foodList);
                    }});
                }
            }
        }
    }

    private void serveQueue() {
        while (!ActionsQueue.eating.isEmpty()) {
            Map<IslandObject, List<IslandObject>> eatingMap = ActionsQueue.eating.remove();
            for (var eatingEntry : eatingMap.entrySet()) {
                IslandObject islandObject = eatingEntry.getKey();
                List<IslandObject> foodList = eatingEntry.getValue();
                island.eatFromFoodList(islandObject, foodList);
                foodList.forEach(island::decrementObjectOnField);
            }
        }
    }

}
