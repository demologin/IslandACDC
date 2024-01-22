package com.javarush.island.berezovskiy.Entities.Organism.Animals.Herbivores;

import com.javarush.island.berezovskiy.Configs.OrganismConfigs;
import com.javarush.island.berezovskiy.Constants.Constants;

import java.util.concurrent.atomic.AtomicInteger;

public class Caterpillar extends Herbivorous{
    private static final AtomicInteger caterpillarNumber = new AtomicInteger(0);
    public Caterpillar(){
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
        Caterpillar.caterpillarNumber.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
       Caterpillar.caterpillarNumber.decrementAndGet();
    }
}
