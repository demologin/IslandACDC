package com.javarush.island.boyarinov.entities.map;

import com.javarush.island.boyarinov.entities.organism.Organisms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cell {

    private final Set<Organisms> organismsSet = new HashSet<>();
    private final List<Cell> availableCells = new ArrayList<>();

    public Cell() {
    }

    public void initAvailableCells(Island island, int row, int column) {
        Cell[][] map = island.getMap();
        if (row > 0) {
            availableCells.add(map[row - 1][column]);
        }
        if (column > 0) {
            availableCells.add(map[row][column - 1]);
        }
        if (row < island.getRow() - 1) {
            availableCells.add(map[row + 1][column]);
        }
        if (column < island.getColumn() - 1) {
            availableCells.add(map[row][column + 1]);
        }
    }

    public Set<Organisms> getOrganismsSet() {
        return organismsSet;
    }

    public List<Cell> getAvailableCells() {
        return availableCells;
    }
}
