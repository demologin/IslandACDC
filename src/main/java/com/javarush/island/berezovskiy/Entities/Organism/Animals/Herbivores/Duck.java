package com.javarush.island.berezovskiy.Entities.Organism.Animals.Herbivores;

import com.javarush.island.berezovskiy.Configs.OrganismConfigs;
import com.javarush.island.berezovskiy.Constants.Constants;

import java.util.concurrent.atomic.AtomicInteger;

public class Duck extends Herbivorous{
    private static final AtomicInteger duckNumber = new AtomicInteger(0);
    public Duck(){
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
        Duck.duckNumber.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
       Duck.duckNumber.decrementAndGet();
    }
}
