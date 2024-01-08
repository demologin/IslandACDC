package com.javarush.island.berezovskiy.Entities.Organism;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Organism {
    private int coordinateY;
    private int coordinateX;
    public abstract void reproduce();

    protected static AtomicInteger organismNumber = new AtomicInteger();
    protected static int increaseOrganismNumber() {
        return organismNumber.incrementAndGet();
    }
}
