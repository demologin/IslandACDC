package com.javarush.island.boyarinov.entities.organism;

public abstract class Organisms {

    private String name;
    private double weight;


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

    @Override
    public String toString() {
        return "Organisms{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
