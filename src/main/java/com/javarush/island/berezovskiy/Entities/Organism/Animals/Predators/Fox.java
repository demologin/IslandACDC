package com.javarush.island.berezovskiy.Entities.Organism.Animals.Predators;

import com.javarush.island.berezovskiy.Configs.OrganismConfigs;
import com.javarush.island.berezovskiy.Constants.Constants;

import java.util.concurrent.atomic.AtomicInteger;

public class Fox extends Predator {
    private static final AtomicInteger foxNumber = new AtomicInteger(0);
    public Fox() {
        super();
        maximumCount = OrganismConfigs.MAX_FOX_COUNT_IN_CELL;
        this.name = Constants.FOX;
        maximumStep = OrganismConfigs.MAX_FOX_STEP;
        Fox.foxNumber.incrementAndGet();
    }

    public static AtomicInteger getOrganismNumber() {
        return Fox.foxNumber;
    }
    @Override
    public void incrementOrganismCount() {
        Fox.organismAmount.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
        Fox.organismAmount.decrementAndGet();
    }
}
