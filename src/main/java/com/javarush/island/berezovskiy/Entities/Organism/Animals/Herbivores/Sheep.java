package com.javarush.island.berezovskiy.Entities.Organism.Animals.Herbivores;

import com.javarush.island.berezovskiy.Configs.OrganismConfigs;
import com.javarush.island.berezovskiy.Constants.Constants;
import com.javarush.island.berezovskiy.Entities.Organism.Organism;

import java.util.concurrent.atomic.AtomicInteger;

public class Sheep extends Herbivorous{
    private static final AtomicInteger sheepNumber = new AtomicInteger(0);
    public Sheep(){
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
