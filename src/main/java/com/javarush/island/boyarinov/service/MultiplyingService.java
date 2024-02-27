package com.javarush.island.boyarinov.service;

import com.javarush.island.boyarinov.entities.map.Cell;
import com.javarush.island.boyarinov.entities.map.Island;

public class MultiplyingService extends AbstractService {
    private final Island island;

    public MultiplyingService(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        Cell[][] map = island.getMap();
        for (Cell[] row : map) {
            for (Cell cell : row) {
                processOneCell(cell, o -> o.multiply(cell));
            }
        }
    }
}

