package com.javarush.island.berezovskiy.entities.organism.animals.predators;

import com.javarush.island.berezovskiy.configs.OrganismConfigs;
import com.javarush.island.berezovskiy.constants.Constants;
import com.javarush.island.berezovskiy.entities.organism.Organism;

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
        Organism.organismAmount.incrementAndGet();
        Bear.bearNumber.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
        Organism.organismAmount.decrementAndGet();
        Bear.bearNumber.decrementAndGet();
    }
}
