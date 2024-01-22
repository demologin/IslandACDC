package com.javarush.island.berezovskiy.Entities.Organism.Animals.Herbivores;

import com.javarush.island.berezovskiy.Configs.OrganismConfigs;
import com.javarush.island.berezovskiy.Constants.Constants;
import com.javarush.island.berezovskiy.Entities.Organism.Organism;

import java.util.concurrent.atomic.AtomicInteger;

public class Deer extends Herbivorous{
    private static final AtomicInteger deerNumber = new AtomicInteger(0);
    public Deer(){
        super();
        this.maximumCount = OrganismConfigs.MAX_DEER_COUNT_IN_CELL;
        this.name = Constants.DEER;
        maximumStep = OrganismConfigs.MAX_DEER_STEP;
        Deer.deerNumber.incrementAndGet();
    }

    public static AtomicInteger getOrganismNumber() {
        return Deer.deerNumber;
    }

    @Override
    public void incrementOrganismCount() {
        Organism.organismAmount.incrementAndGet();
        Deer.deerNumber.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
        Organism.organismAmount.decrementAndGet();
        Deer.deerNumber.decrementAndGet();
    }
}
