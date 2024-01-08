package com.javarush.island.berezovskiy.Entities.Organism.Animals.Predators;

import com.javarush.island.berezovskiy.Entities.Organism.Animals.Animal;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Predator extends Animal {
    protected static AtomicInteger predatorsNumber = new AtomicInteger();
    protected static int increaseOrganismNumber() {
        return predatorsNumber.incrementAndGet();
    }
}
