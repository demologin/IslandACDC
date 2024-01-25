package com.javarush.island.berezovskiy.entities.organism.animals.herbivores;

import com.javarush.island.berezovskiy.configs.OrganismConfigs;
import com.javarush.island.berezovskiy.constants.Constants;
import com.javarush.island.berezovskiy.entities.organism.Organism;

import java.util.concurrent.atomic.AtomicInteger;

public class Buffalo extends Herbivorous {
    private static final AtomicInteger buffaloNumber = new AtomicInteger(0);

    public Buffalo() {
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
        Organism.organismAmount.incrementAndGet();
        Buffalo.buffaloNumber.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
        Organism.organismAmount.decrementAndGet();
        Buffalo.buffaloNumber.decrementAndGet();
    }
}
