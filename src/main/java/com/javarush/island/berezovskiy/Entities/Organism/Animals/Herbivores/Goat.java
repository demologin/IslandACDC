package com.javarush.island.berezovskiy.Entities.Organism.Animals.Herbivores;

import com.javarush.island.berezovskiy.Configs.OrganismConfigs;
import com.javarush.island.berezovskiy.Constants.Constants;
import com.javarush.island.berezovskiy.Entities.Organism.Organism;

import java.util.concurrent.atomic.AtomicInteger;

public class Goat extends Herbivorous{
    private static final AtomicInteger goatNumber = new AtomicInteger(0);
    public Goat(){
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
