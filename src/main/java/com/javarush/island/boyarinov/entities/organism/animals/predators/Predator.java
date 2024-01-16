package com.javarush.island.boyarinov.entities.organism.animals.predators;

import com.javarush.island.boyarinov.entities.map.Island;
import com.javarush.island.boyarinov.entities.organism.animals.Animal;

public class Predator extends Animal{

    public Predator(Island island) {
        super(island);
    }

    @Override
    public void die() {

    }

    @Override
    public void eat() {

    }

    @Override
    public Animal multiply() {
        return null;
    }
}
