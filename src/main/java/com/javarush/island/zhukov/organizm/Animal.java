package com.javarush.island.zhukov.organizm;

import lombok.Getter;


@Getter
public abstract class Animal extends Organism{
    private final int speed;
    private final double fullNotHungry;
    public Animal(double weight, int speed, int maxCountInCell, double fullNotHungry,int x,int y, int id) {
        super(id, weight, x, y, maxCountInCell);
        this.speed = speed;
        this.fullNotHungry = fullNotHungry;
    }
}
