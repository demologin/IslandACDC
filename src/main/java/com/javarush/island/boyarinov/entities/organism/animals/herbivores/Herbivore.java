package com.javarush.island.boyarinov.entities.organism.animals.herbivores;

import com.javarush.island.boyarinov.entities.map.Island;
import com.javarush.island.boyarinov.entities.organism.animals.Animal;

public class Herbivore extends Animal {

    public Herbivore(Island island) {
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
