package com.javarush.island.alimova.entity.alive;

import com.javarush.island.alimova.api.entity.Multiplying;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organism organism = (Organism) o;
        return identification == organism.identification && Double.compare(weight, organism.weight) == 0 && maxAmount == organism.maxAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identification, weight, maxAmount);
    }
}
