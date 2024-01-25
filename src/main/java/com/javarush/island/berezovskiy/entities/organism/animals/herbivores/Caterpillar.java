package com.javarush.island.berezovskiy.entities.organism.animals.herbivores;

import com.javarush.island.berezovskiy.configs.OrganismConfigs;
import com.javarush.island.berezovskiy.constants.Constants;
import com.javarush.island.berezovskiy.entities.organism.Organism;

import java.util.concurrent.atomic.AtomicInteger;

public class Caterpillar extends Herbivorous {
    private static final AtomicInteger caterpillarNumber = new AtomicInteger(0);

    public Caterpillar() {
        super();
        this.maximumCount = OrganismConfigs.MAX_CATERPILLAR_COUNT_IN_CELL;
        this.name = Constants.CATERPILLAR;
        maximumStep = OrganismConfigs.MAX_CATERPILLAR_STEP;
        Caterpillar.caterpillarNumber.incrementAndGet();
    }

    public static AtomicInteger getOrganismNumber() {
        return Caterpillar.caterpillarNumber;
    }

    @Override
    public void incrementOrganismCount() {
        Organism.organismAmount.incrementAndGet();
        Caterpillar.caterpillarNumber.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
        Organism.organismAmount.decrementAndGet();
        Caterpillar.caterpillarNumber.decrementAndGet();
    }
}
