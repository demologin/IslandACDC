package com.javarush.island.zhukov.organizm;

import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Organism{
    @Setter
    private int x;
    @Setter
    private int y;
    private final int ID;
    @Setter
    private double weight;
    private final int maxCountInCell;

    public Organism(int id, double weight, int x, int y, int maxCountInCell) {
        this.ID = id;
        this.weight = weight;
        this.x = x;
        this.y = y;
        this.maxCountInCell = maxCountInCell;
    }
}
