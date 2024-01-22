package com.javarush.island.berezovskiy.Entities.Organism.Animals.Herbivores;

import com.javarush.island.berezovskiy.Configs.OrganismConfigs;
import com.javarush.island.berezovskiy.Constants.Constants;

import java.util.concurrent.atomic.AtomicInteger;

public class Buffalo extends Herbivorous{
    private static final AtomicInteger buffaloNumber = new AtomicInteger(0);
    public Buffalo(){
        super();
        this.maximumCount = OrganismConfigs.MAX_BUFFALO_COUNT_IN_CELL;
        this.name = Constants.BUFFALO;
        maximumStep = OrganismConfigs.MAX_BUFFALO_STEP;
        Buffalo.buffaloNumber.incrementAndGet();
    }

    public static AtomicInteger getOrganismNumber() {
        return Buffalo.buffaloNumber;
    }

    @Override
    public void incrementOrganismCount() {
        Buffalo.buffaloNumber.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
       Buffalo.buffaloNumber.decrementAndGet();
    }
}
