package com.javarush.island.khasanov.entity.animals.predators;

import com.javarush.island.khasanov.entity.animals.Animal;
import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.entity.Position;

public abstract class Predator extends Animal {

    protected Predator(Island island, Position position) {
        super(island, position);
    }

    protected Predator() {
        super();
    }

}
