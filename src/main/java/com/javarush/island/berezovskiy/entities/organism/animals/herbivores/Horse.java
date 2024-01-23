package com.javarush.island.berezovskiy.entities.organism.animals.herbivores;

import com.javarush.island.berezovskiy.configs.OrganismConfigs;
import com.javarush.island.berezovskiy.constants.Constants;
import com.javarush.island.berezovskiy.entities.organism.Organism;

import java.util.concurrent.atomic.AtomicInteger;

public class Horse extends Herbivorous {
    private static final AtomicInteger horseNumber = new AtomicInteger(0);

    public Horse() {
        super();
        maximumCount = OrganismConfigs.MAX_HORSE_COUNT_IN_CELL;
        this.name = Constants.HORSE;
        maximumStep = OrganismConfigs.MAX_HORSE_STEP;
        Horse.horseNumber.incrementAndGet();
    }

    public static AtomicInteger getOrganismNumber() {
        return Horse.horseNumber;
    }

    @Override
    public void incrementOrganismCount() {
        Organism.organismAmount.incrementAndGet();
        Horse.horseNumber.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
        Organism.organismAmount.decrementAndGet();
        Horse.horseNumber.decrementAndGet();
    }
}
