package com.javarush.island.berezovskiy.entities.organism;

import com.javarush.island.berezovskiy.entities.cell.Cell;
import com.javarush.island.berezovskiy.factory.OrganismFactory;
import com.javarush.island.berezovskiy.interfaces.ChangeOrganismCount;
import com.javarush.island.berezovskiy.interfaces.Reproducible;
import com.javarush.island.berezovskiy.utils.Rnd;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Organism implements Reproducible, ChangeOrganismCount {

    protected OrganismFactory organismFactory = new OrganismFactory();
    protected String name;
    protected Cell cell;
    protected int totalCount;
    protected int maximumCount;
    protected int maximumStep;
    protected boolean isAlive = true;

    public void setNotReadyToGiveBirth(boolean notReadyToGiveBirth) {
        this.notReadyToGiveBirth = notReadyToGiveBirth;
    }

    protected boolean starved = true;
    protected boolean notReadyToGiveBirth = true;
    protected int id = Rnd.getRandom();
    protected String organismType;
    protected static AtomicInteger organismAmount = new AtomicInteger();

    protected Organism() {
        Organism.organismAmount.incrementAndGet();
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


    public boolean isAlive() {
        return isAlive;
    }

    public void setStarved(boolean starved) {
        this.starved = starved;
    }

    public boolean isStarved() {
        return starved;
    }

    public boolean isNotReadyToGiveBirth() {
        return notReadyToGiveBirth;
    }

    public static AtomicInteger getOrganismNumber() {
        return organismAmount;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Organism organism = (Organism) object;

        if (totalCount != organism.totalCount) return false;
        if (maximumCount != organism.maximumCount) return false;
        if (maximumStep != organism.maximumStep) return false;
        if (isAlive != organism.isAlive) return false;
        if (starved != organism.starved) return false;
        if (notReadyToGiveBirth != organism.notReadyToGiveBirth) return false;
        if (id != organism.id) return false;
        if (!Objects.equals(organismFactory, organism.organismFactory))
            return false;
        if (!Objects.equals(name, organism.name)) return false;
        if (!Objects.equals(cell, organism.cell)) return false;
        return Objects.equals(organismType, organism.organismType);
    }

    @Override
    public int hashCode() {
        int result = organismFactory != null ? organismFactory.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (cell != null ? cell.hashCode() : 0);
        result = 31 * result + totalCount;
        result = 31 * result + maximumCount;
        result = 31 * result + maximumStep;
        result = 31 * result + (isAlive ? 1 : 0);
        result = 31 * result + (starved ? 1 : 0);
        result = 31 * result + (notReadyToGiveBirth ? 1 : 0);
        result = 31 * result + id;
        result = 31 * result + (organismType != null ? organismType.hashCode() : 0);
        return result;
    }
}

