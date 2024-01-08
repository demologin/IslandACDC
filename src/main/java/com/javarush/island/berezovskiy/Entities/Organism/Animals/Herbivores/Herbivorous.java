package com.javarush.island.berezovskiy.Entities.Organism.Animals.Herbivores;

import com.javarush.island.berezovskiy.Entities.Organism.Animals.Animal;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Herbivorous extends Animal {
    protected static AtomicInteger herbivoresNumber = new AtomicInteger();
    protected static int increaseOrganismNumber() {
        return herbivoresNumber.incrementAndGet();
    }
}
