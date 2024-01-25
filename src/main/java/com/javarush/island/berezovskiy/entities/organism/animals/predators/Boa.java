package com.javarush.island.berezovskiy.entities.organism.animals.predators;

import com.javarush.island.berezovskiy.configs.OrganismConfigs;
import com.javarush.island.berezovskiy.constants.Constants;
import com.javarush.island.berezovskiy.entities.organism.Organism;

import java.util.concurrent.atomic.AtomicInteger;

public class Boa extends Predator {
    private static final AtomicInteger boaNumber = new AtomicInteger(0);
    public Boa() {
        super();
        this.maximumCount = OrganismConfigs.MAX_BOA_COUNT_IN_CELL;
        this.name = Constants.BOA;
        maximumStep = OrganismConfigs.MAX_BOA_STEP;
        Boa.boaNumber.incrementAndGet();
    }

    public static AtomicInteger getOrganismNumber() {
        return Boa.boaNumber;
    }
    @Override
    public void incrementOrganismCount() {
        Organism.organismAmount.incrementAndGet();
        Boa.boaNumber.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
        Organism.organismAmount.decrementAndGet();
        Boa.boaNumber.decrementAndGet();
    }
}
