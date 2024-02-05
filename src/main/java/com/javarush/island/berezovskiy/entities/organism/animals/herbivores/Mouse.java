package com.javarush.island.berezovskiy.entities.organism.animals.herbivores;

import com.javarush.island.berezovskiy.configs.OrganismConfigs;
import com.javarush.island.berezovskiy.constants.Constants;
import com.javarush.island.berezovskiy.entities.organism.Organism;

import java.util.concurrent.atomic.AtomicInteger;

public class Mouse extends Herbivorous {
    private static final AtomicInteger mouseNumber = new AtomicInteger(0);

    public Mouse() {
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
        Organism.organismAmount.incrementAndGet();
        Mouse.mouseNumber.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
        Organism.organismAmount.decrementAndGet();
        Mouse.mouseNumber.decrementAndGet();
    }
}
