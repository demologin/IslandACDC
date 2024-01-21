package com.javarush.island.khasanov.repository;

import com.javarush.island.khasanov.entity.*;
import com.javarush.island.khasanov.entity.animals.herbivores.Rabbit;
import com.javarush.island.khasanov.entity.animals.predators.Wolf;
import com.javarush.island.khasanov.entity.plants.Grass;

public enum Prototypes {
    WOLF(new Wolf()),
    RABBIT(new Rabbit()),
    GRASS(new Grass());


    private final IslandObject islandObject;

    Prototypes(IslandObject islandObject) {
        this.islandObject = islandObject;
    }

    public static IslandObject get(String islandObjectName) {
        Prototypes found = Prototypes.valueOf(islandObjectName.toUpperCase());
        return found.islandObject;
    }
}
