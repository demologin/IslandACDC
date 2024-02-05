package com.javarush.island.berezovskiy.entities.organism.animals.herbivores;

import com.javarush.island.berezovskiy.configs.OrganismConfigs;
import com.javarush.island.berezovskiy.constants.Constants;
import com.javarush.island.berezovskiy.entities.organism.Organism;

import java.util.concurrent.atomic.AtomicInteger;

public class Goat extends Herbivorous {
    private static final AtomicInteger goatNumber = new AtomicInteger(0);

    public Goat() {
        super();
        this.maximumCount = OrganismConfigs.MAX_GOAT_COUNT_IN_CELL;
        this.name = Constants.GOAT;
        maximumStep = OrganismConfigs.MAX_GOAT_STEP;
        Goat.goatNumber.incrementAndGet();
    }

    public static AtomicInteger getOrganismNumber() {
        return Goat.goatNumber;
    }

    @Override
    public void incrementOrganismCount() {
        Organism.organismAmount.incrementAndGet();
        Goat.goatNumber.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
        Organism.organismAmount.decrementAndGet();
        Goat.goatNumber.decrementAndGet();
    }
}
