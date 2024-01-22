package com.javarush.island.berezovskiy.Entities.Organism.Animals.Herbivores;

import com.javarush.island.berezovskiy.Configs.OrganismConfigs;
import com.javarush.island.berezovskiy.Constants.Constants;

import java.util.concurrent.atomic.AtomicInteger;

public class Mouse extends Herbivorous{
    private static final AtomicInteger mouseNumber = new AtomicInteger(0);
    public Mouse(){
        super();
        this.maximumCount = OrganismConfigs.MAX_MOUSE_COUNT_IN_CELL;
        this.name = Constants.MOUSE;
        maximumStep = OrganismConfigs.MAX_MOUSE_STEP;
        Mouse.mouseNumber.incrementAndGet();
    }

    public static AtomicInteger getOrganismNumber() {
        return Mouse.mouseNumber;
    }

    @Override
    public void incrementOrganismCount() {
        Mouse.mouseNumber.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
       Mouse.mouseNumber.decrementAndGet();
    }
}
