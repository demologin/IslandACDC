package com.javarush.island.berezovskiy.Entities.Organism.Plants;

import com.javarush.island.berezovskiy.Configs.OrganismConfigs;
import com.javarush.island.berezovskiy.Constants.Constants;

import java.util.concurrent.atomic.AtomicInteger;

public class Grass extends Plant{
    private static final AtomicInteger grassNumber = new AtomicInteger(0);
    public Grass(){
        super();
        this.maximumCount = OrganismConfigs.MAX_GRASS_COUNT_IN_CELL;
        this.name = Constants.GRASS;
        Grass.grassNumber.incrementAndGet();
    }
    @Override
    public void incrementOrganismCount() {
        Grass.organismAmount.incrementAndGet();
    }
    public static AtomicInteger getOrganismNumber() {
        return Grass.grassNumber;
    }

    @Override
    public void decrementOrganismCount() {
        Grass.organismAmount.decrementAndGet();
    }

}
