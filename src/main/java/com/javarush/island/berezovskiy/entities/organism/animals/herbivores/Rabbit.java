package com.javarush.island.berezovskiy.entities.organism.animals.herbivores;

import com.javarush.island.berezovskiy.configs.OrganismConfigs;
import com.javarush.island.berezovskiy.constants.Constants;
import com.javarush.island.berezovskiy.entities.organism.Organism;

import java.util.concurrent.atomic.AtomicInteger;

public class Rabbit extends Herbivorous {
    private static final AtomicInteger rabbitNumber = new AtomicInteger(0);

    public Rabbit() {
        super();
        this.maximumCount = OrganismConfigs.MAX_RABBIT_COUNT_IN_CELL;
        this.name = Constants.RABBIT;
        maximumStep = OrganismConfigs.MAX_RABBIT_STEP;
        Rabbit.rabbitNumber.incrementAndGet();
    }

    public static AtomicInteger getOrganismNumber() {
        return Rabbit.rabbitNumber;
    }

    @Override
    public void incrementOrganismCount() {
        Organism.organismAmount.incrementAndGet();
        Rabbit.rabbitNumber.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
        Organism.organismAmount.decrementAndGet();
        Rabbit.rabbitNumber.decrementAndGet();
    }
}
