package com.javarush.island.berezovskiy.Entities.Organism.Animals.Predators;

import com.javarush.island.berezovskiy.Configs.OrganismConfigs;
import com.javarush.island.berezovskiy.Constants.Constants;

import java.util.concurrent.atomic.AtomicInteger;

public class Eagle extends Predator {
    private static final AtomicInteger eagleNumber = new AtomicInteger(0);
    public Eagle() {
        super();
        maximumCount = OrganismConfigs.MAX_EAGLE_COUNT_IN_CELL;
        this.name = Constants.EAGLE;
        maximumStep = OrganismConfigs.MAX_EAGLE_STEP;
        Eagle.eagleNumber.incrementAndGet();
    }

    public static AtomicInteger getOrganismNumber() {
        return Eagle.eagleNumber;
    }
    @Override
    public void incrementOrganismCount() {
        Eagle.organismAmount.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
        Eagle.organismAmount.decrementAndGet();
    }
}
