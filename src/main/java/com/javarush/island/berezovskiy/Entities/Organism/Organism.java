package com.javarush.island.berezovskiy.Entities.Organism;

import com.javarush.island.berezovskiy.Entities.Cell.Cell;
import com.javarush.island.berezovskiy.Interfaces.ChangeOrganismCount;
import com.javarush.island.berezovskiy.Interfaces.Reproducible;
import com.javarush.island.berezovskiy.Utils.Rnd;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Organism implements Reproducible, ChangeOrganismCount {

    protected int totalCount;
    protected String name;
    protected int maximumCount;
    protected Cell cell;
    protected boolean isAlive = true;

    public void setNotReadyToGiveBirth(boolean notReadyToGiveBirth) {
        this.notReadyToGiveBirth = notReadyToGiveBirth;
    }

    protected boolean starved = true;
    protected boolean notReadyToGiveBirth = true;
    protected int id = Rnd.getRandom();
    protected String organismType;
    protected static AtomicInteger organismAmount = new AtomicInteger();
    protected Organism(){
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
    public String getOrganismType() {
        return organismType;
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

