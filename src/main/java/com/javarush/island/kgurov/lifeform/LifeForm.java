package com.javarush.island.kgurov.lifeform;

import lombok.Getter;

@Getter
public class LifeForm {
    private final double weight;
    private final int maxPopulation;
    private final String name;
    private int row;
    private int col;
    public LifeForm(double weight, int maxPopulation, String name) {
        this.weight = weight;
        this.maxPopulation = maxPopulation;
        this.name = name;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setCol(int col) {
        this.col = col;
    }
}
