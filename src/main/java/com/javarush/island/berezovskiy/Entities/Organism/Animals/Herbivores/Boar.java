package com.javarush.island.berezovskiy.Entities.Organism.Animals.Herbivores;

import com.javarush.island.berezovskiy.Configs.OrganismConfigs;
import com.javarush.island.berezovskiy.Constants.Constants;
import com.javarush.island.berezovskiy.Entities.Organism.Organism;

import java.util.concurrent.atomic.AtomicInteger;

public class Boar extends Herbivorous{

    private static final AtomicInteger boarNumber = new AtomicInteger(0);
    public Boar(){
        super();
        maximumCount = OrganismConfigs.MAX_BOAR_COUNT_IN_CELL;
        this.name = Constants.BOAR;
        maximumStep = OrganismConfigs.MAX_BOAR_STEP;
        Boar.boarNumber.incrementAndGet();
    }

    public static AtomicInteger getOrganismNumber() {
        return Boar.boarNumber;
    }

    @Override
    public void incrementOrganismCount() {
        Organism.organismAmount.incrementAndGet();
        Boar.boarNumber.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
        Organism.organismAmount.decrementAndGet();
        Boar.boarNumber.decrementAndGet();
    }
}
