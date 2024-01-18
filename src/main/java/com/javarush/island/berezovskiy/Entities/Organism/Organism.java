package com.javarush.island.berezovskiy.Entities.Organism;

import com.javarush.island.berezovskiy.Entities.Cell.Cell;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Organism implements Reproducible, ChangeOrganismCount{

    protected int totalCount;
    protected String name;
    protected int maximumCount;
    protected Cell cell;
    protected boolean isAlive;
    protected boolean starved;
    protected boolean notReadyToGiveBirth;
    protected int id = ThreadLocalRandom.current().nextInt();

    public static AtomicInteger getOrganismNumber() {
        return organismNumber;
    }

    protected static AtomicInteger organismNumber = new AtomicInteger();
    protected Organism(){
        organismNumber.incrementAndGet();
    }

    public String getName() {
        return name;
    }
    public int getMaximumCount() {
        return maximumCount;
    }
    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isStarved() {
        return starved;
    }

    public boolean isNotReadyToGiveBirth() {
        return notReadyToGiveBirth;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Organism organism = (Organism) object;

        if (totalCount != organism.totalCount) return false;
        if (maximumCount != organism.maximumCount) return false;
        if (id != organism.id) return false;
        return Objects.equals(name, organism.name);
    }

    @Override
    public int hashCode() {
        int result = totalCount;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + maximumCount;
        result = 31 * result + id;
        return result;
    }
}

