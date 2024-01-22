package com.javarush.island.berezovskiy.Entities.Organism.Animals.Predators;

import com.javarush.island.berezovskiy.Configs.OrganismConfigs;
import com.javarush.island.berezovskiy.Constants.Constants;

import java.util.concurrent.atomic.AtomicInteger;

public class Bear extends Predator {
    private static final AtomicInteger bearNumber = new AtomicInteger(0);
    public Bear() {
        super();
        maximumCount = OrganismConfigs.MAX_BEAR_COUNT_IN_CELL;
        this.name = Constants.BEAR;
        maximumStep = OrganismConfigs.MAX_BEAR_STEP;
        Bear.bearNumber.incrementAndGet();
    }

    public static AtomicInteger getOrganismNumber() {
        return Bear.bearNumber;
    }
    @Override
    public void incrementOrganismCount() {
        Bear.organismAmount.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
        Bear.organismAmount.decrementAndGet();
    }
}
