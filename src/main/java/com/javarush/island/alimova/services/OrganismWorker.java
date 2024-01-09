package com.javarush.island.alimova.services;

import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.entity.alive.plants.Plant;
import com.javarush.island.alimova.entity.map.Cell;

import java.util.Set;

public class OrganismWorker implements Runnable{

    private final Cell cell;

    public OrganismWorker(Cell cell) {
        this.cell = cell;
    }

    @Override
    public void run() {
        Set<Organism> organismSet = cell.getSetCreatures();
        for (Organism organism : organismSet) {
            if(organism instanceof Plant plant) {
                plant.multiply(cell);
            }
        }
        cell.addOrganismsFromQueue();
    }
}
