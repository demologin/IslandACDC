package com.javarush.island.berezovskiy.Entities.Organism.Animals.Herbivores;

import com.javarush.island.berezovskiy.Configs.OrganismConfigs;
import com.javarush.island.berezovskiy.Constants.Constants;

import java.util.concurrent.atomic.AtomicInteger;

public class Horse extends Herbivorous{
    private static final AtomicInteger horseNumber = new AtomicInteger(0);
    public Horse(){
        super();
        this.maximumCount = OrganismConfigs.MAX_HORSE_COUNT_IN_CELL;
        this.name = Constants.HORSE;
        maximumStep = OrganismConfigs.MAX_HORSE_STEP;
        Horse.horseNumber.incrementAndGet();
    }

    public static AtomicInteger getOrganismNumber() {
        return Horse.horseNumber;
    }

    @Override
    public void incrementOrganismCount() {
        Horse.horseNumber.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
       Horse.horseNumber.decrementAndGet();
    }
}
