package com.javarush.island.khasanov.service;

import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.repository.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MovingService implements Runnable {
    private final Island island;

    public MovingService(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        fillMovementsQueue();
        serveQueue();
    }

    private void fillMovementsQueue() {
        for (Map.Entry<Position, Set<IslandObject>> entry : island.getIslandMap().entrySet()) {
            for (IslandObject islandObject : entry.getValue()) {
                Position newPosition = islandObject.move();
                Position currentPosition = islandObject.getPosition();

                if (!currentPosition.equals(newPosition)) {
                    ActionsQueue.movements.add(new HashMap<>() {{
                        put(islandObject, newPosition);
                    }});
                }
            }
        }
    }

    private void serveQueue() {
        while (!ActionsQueue.movements.isEmpty()) {
            Map<IslandObject, Position> movementsMap = ActionsQueue.movements.remove();
            for (var entryMove : movementsMap.entrySet()) {
                IslandObject object = entryMove.getKey();
                Position newPosition = entryMove.getValue();

                island.moveIslandObject(newPosition, object);
            }
        }
    }

}
