package com.javarush.island.zhukov.organizm.herbivores;

import com.javarush.island.zhukov.organizm.Animal;

public abstract class Herbivores extends Animal {

    public Herbivores(double weight, int speed, int maxCountInCell, double fullNotHungry, int x, int y, int id) {
        super(weight, speed, maxCountInCell, fullNotHungry, x, y, id);
    }
}
