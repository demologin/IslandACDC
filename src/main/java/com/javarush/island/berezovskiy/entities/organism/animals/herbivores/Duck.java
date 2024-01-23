package com.javarush.island.berezovskiy.entities.organism.animals.herbivores;

import com.javarush.island.berezovskiy.configs.OrganismConfigs;
import com.javarush.island.berezovskiy.constants.Constants;
import com.javarush.island.berezovskiy.entities.organism.Organism;

import java.util.concurrent.atomic.AtomicInteger;

public class Duck extends Herbivorous {
    private static final AtomicInteger duckNumber = new AtomicInteger(0);

    public Duck() {
        super();
        this.maximumCount = OrganismConfigs.MAX_DUCK_COUNT_IN_CELL;
        this.name = Constants.DUCK;
        maximumStep = OrganismConfigs.MAX_DUCK_STEP;
        Duck.duckNumber.incrementAndGet();
    }

    public static AtomicInteger getOrganismNumber() {
        return Duck.duckNumber;
    }

    @Override
    public void incrementOrganismCount() {
        Organism.organismAmount.incrementAndGet();
        Duck.duckNumber.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
        Organism.organismAmount.decrementAndGet();
        Duck.duckNumber.decrementAndGet();
    }
}
