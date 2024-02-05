package com.javarush.island.berezovskiy.entities.organism.animals.predators;

import com.javarush.island.berezovskiy.configs.OrganismConfigs;
import com.javarush.island.berezovskiy.constants.Constants;
import com.javarush.island.berezovskiy.entities.organism.Organism;

import java.util.concurrent.atomic.AtomicInteger;

public class Eagle extends Predator {
    private static final AtomicInteger eagleNumber = new AtomicInteger(0);
    public Eagle() {
        super();
        this.maximumCount = OrganismConfigs.MAX_EAGLE_COUNT_IN_CELL;
        this.name = Constants.EAGLE;
        maximumStep = OrganismConfigs.MAX_EAGLE_STEP;
        Eagle.eagleNumber.incrementAndGet();
    }

    public static AtomicInteger getOrganismNumber() {
        return Eagle.eagleNumber;
    }
    @Override
    public void incrementOrganismCount() {
        Organism.organismAmount.incrementAndGet();
        Eagle.eagleNumber.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
        Organism.organismAmount.decrementAndGet();
        Eagle.eagleNumber.decrementAndGet();
    }
}
