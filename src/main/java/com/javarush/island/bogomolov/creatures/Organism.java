package com.javarush.island.bogomolov.creatures;

public abstract class Organism {
    private final String name;
    private final double weight;
    private final int limit;
    private int row;
    private int column;

    public Organism(String name, double weight, int limit) {
        this.name = name;
        this.weight = weight;
        this.limit = limit;
    }

    public String getName() {
        return name;
    }



    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public double getWeight() {
        return weight;
    }

    public int getLimit() {
        return limit;
    }
}
