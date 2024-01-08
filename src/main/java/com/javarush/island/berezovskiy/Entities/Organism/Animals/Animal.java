package com.javarush.island.berezovskiy.Entities.Organism.Animals;

import com.javarush.island.berezovskiy.Entities.Organism.Movable;
import com.javarush.island.berezovskiy.Entities.Organism.Organism;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Animal extends Organism implements Movable {
    public abstract void eat();

    protected static AtomicInteger animalNumber = new AtomicInteger();
    protected static int increaseOrganismNumber() {
        return animalNumber.incrementAndGet();
    }
}
