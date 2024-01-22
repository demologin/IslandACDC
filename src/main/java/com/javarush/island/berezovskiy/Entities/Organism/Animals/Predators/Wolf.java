package com.javarush.island.berezovskiy.Entities.Organism.Animals.Predators;

import com.javarush.island.berezovskiy.Configs.OrganismConfigs;
import com.javarush.island.berezovskiy.Constants.Constants;

import java.util.concurrent.atomic.AtomicInteger;

public class Wolf extends Predator {
    private static final AtomicInteger wolfNumber = new AtomicInteger(0);
    public Wolf() {
        super();
        maximumCount = OrganismConfigs.MAX_WOLF_COUNT_IN_CELL;
        this.name = Constants.WOLF;
        maximumStep = OrganismConfigs.MAX_WOLF_STEP;
        Wolf.wolfNumber.incrementAndGet();
    }
    public static AtomicInteger getOrganismNumber() {
        return Wolf.wolfNumber;
    }
    @Override
    public void incrementOrganismCount() {
        Wolf.wolfNumber.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
        Wolf.wolfNumber.decrementAndGet();
    }
}
