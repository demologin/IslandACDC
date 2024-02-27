package com.javarush.island.boyarinov.service;

import com.javarush.island.boyarinov.entities.map.Cell;
import com.javarush.island.boyarinov.entities.organism.Organisms;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public abstract class AbstractService implements Runnable {

    protected void processOneCell(Cell cell, Consumer<Organisms> action) {
        Set<Organisms> allOrganisms = getSafetyOrganismSet(cell);
        allOrganisms.forEach(action);
    }

    private Set<Organisms> getSafetyOrganismSet(Cell cell) {
        cell.getLock().lock();
        try {
            Set<Organisms> organismsSet = cell.getOrganismsSet();
            return new HashSet<>(organismsSet);

        } finally {
            cell.getLock().unlock();
        }
    }
}
