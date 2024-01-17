package com.javarush.island.boyarinov.entities.organism;

import com.javarush.island.boyarinov.entities.map.Cell;
import com.javarush.island.boyarinov.entities.map.Island;

public abstract class Organisms {

    private String name;
    private int weight;
    private int maxOfAnimalsToCell;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMaxOfAnimalsToCell() {
        return maxOfAnimalsToCell;
    }

    public void setMaxOfAnimalsToCell(int maxOfAnimalsToCell) {
        this.maxOfAnimalsToCell = maxOfAnimalsToCell;
    }

}
