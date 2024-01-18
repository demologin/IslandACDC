package com.javarush.island.berezovskiy.Entities.Organism.Plants;

import java.util.concurrent.atomic.AtomicInteger;

public class Grass extends Plant{
    private static AtomicInteger grassCount = new AtomicInteger();
    @Override
    public void incrementOrganismCount() {
        Grass.grassCount.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
        Grass.grassCount.decrementAndGet();
    }

}
