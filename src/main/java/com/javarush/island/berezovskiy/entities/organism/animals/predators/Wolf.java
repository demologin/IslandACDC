package com.javarush.island.berezovskiy.entities.organism.animals.predators;

import com.javarush.island.berezovskiy.configs.OrganismConfigs;
import com.javarush.island.berezovskiy.constants.Constants;
import com.javarush.island.berezovskiy.entities.organism.Organism;

import java.util.concurrent.atomic.AtomicInteger;

public class Wolf extends Predator {
    private static final AtomicInteger wolfNumber = new AtomicInteger(0);
    public Wolf() {
        super();
        this.maximumCount = OrganismConfigs.MAX_WOLF_COUNT_IN_CELL;
        this.name = Constants.WOLF;
        maximumStep = OrganismConfigs.MAX_WOLF_STEP;
        Wolf.wolfNumber.incrementAndGet();
    }
    public static AtomicInteger getOrganismNumber() {
        return Wolf.wolfNumber;
    }
    @Override
    public void incrementOrganismCount() {
        Organism.organismAmount.incrementAndGet();
        Wolf.wolfNumber.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
        Organism.organismAmount.decrementAndGet();
        Wolf.wolfNumber.decrementAndGet();
    }
}
