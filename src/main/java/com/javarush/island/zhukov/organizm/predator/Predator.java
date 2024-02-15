package com.javarush.island.zhukov.organizm.predator;

import com.javarush.island.zhukov.organizm.Animal;

public abstract class Predator extends Animal {


    public Predator(double weight, int speed, int maxCountInCell, double fullNotHungry, int x, int y, int id) {
        super(weight, speed, maxCountInCell, fullNotHungry, x, y, id);
    }
}
