package com.javarush.island.alimova.entity.alive;

import com.javarush.island.alimova.api.entity.Multiplying;

import java.util.concurrent.atomic.AtomicLong;

public abstract class Organism implements Multiplying, Cloneable {

    public static AtomicLong indexStation = new AtomicLong(1);

    protected final long identification;

    protected double weight;
    protected int maxAmount;

    public Organism(double weight, int maxAmount) {
        identification = indexStation.getAndIncrement();
        this.weight = weight;
        this.maxAmount = maxAmount;
    }

    @Override
    public abstract Organism clone() throws CloneNotSupportedException;
}
