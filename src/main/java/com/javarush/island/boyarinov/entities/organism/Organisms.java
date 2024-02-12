package com.javarush.island.boyarinov.entities.organism;

import com.javarush.island.boyarinov.constants.Limit;
import com.javarush.island.boyarinov.entities.map.Cell;
import com.javarush.island.boyarinov.interfaces.Multiplying;
import com.javarush.island.boyarinov.util.RandomNum;

public abstract class Organisms implements Multiplying, Cloneable {

    private String name;
    private double weight;

    public Organisms(String name) {
        this.name = name;
    }

    @Override
    public Organisms multiply(Cell Cell) {
        return null;
    }

    @Override
    public Organisms clone() {
        try {
            Organisms clone = (Organisms) super.clone();
            double maxWeight = Limit.getMaxWeight().get(name);
            double weight = RandomNum.getRndNumber(maxWeight / 2, maxWeight);
            clone.setWeight(weight);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
