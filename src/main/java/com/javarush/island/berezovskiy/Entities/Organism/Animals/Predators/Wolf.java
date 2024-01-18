package com.javarush.island.berezovskiy.Entities.Organism.Animals.Predators;

import com.javarush.island.berezovskiy.Configs.Configs;
import com.javarush.island.berezovskiy.Constants.Constants;

import java.util.concurrent.atomic.AtomicInteger;

public class Wolf extends Predator {
    public static AtomicInteger getWolfNumber() {
        return wolfNumber;
    }

    protected static AtomicInteger wolfNumber = new AtomicInteger();

    protected static int increaseOrganismNumber() {
        return wolfNumber.incrementAndGet();
    }

    public Wolf() {
        maximumCount = Configs.MAX_WOLF_COUNT_IN_CELL;
        this.name = Constants.WOLF;
        maximumStep = Configs.MAX_STEP_WOLF;
        wolfNumber.incrementAndGet();
    }

    @Override
    public void incrementOrganismCount() {
        Wolf.wolfNumber.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
        Wolf.wolfNumber.decrementAndGet();
    }
}
