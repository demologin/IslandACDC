package com.javarush.island.berezovskiy.entities.organism.animals.herbivores;

import com.javarush.island.berezovskiy.configs.OrganismConfigs;
import com.javarush.island.berezovskiy.constants.Constants;
import com.javarush.island.berezovskiy.entities.organism.Organism;

import java.util.concurrent.atomic.AtomicInteger;

public class Sheep extends Herbivorous {
    private static final AtomicInteger sheepNumber = new AtomicInteger(0);

    public Sheep() {
        super();
        this.maximumCount = OrganismConfigs.MAX_SHEEP_COUNT_IN_CELL;
        this.name = Constants.SHEEP;
        maximumStep = OrganismConfigs.MAX_SHEEP_STEP;
        Sheep.sheepNumber.incrementAndGet();
    }

    public static AtomicInteger getOrganismNumber() {
        return Sheep.sheepNumber;
    }

    @Override
    public void incrementOrganismCount() {
        Organism.organismAmount.incrementAndGet();
        Sheep.sheepNumber.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
        Organism.organismAmount.decrementAndGet();
        Sheep.sheepNumber.decrementAndGet();
    }
}
