package com.javarush.island.khasanov.entity.animals.herbivores;

import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.entity.Position;

public class Horse extends Herbivore {
    public Horse(Island island, Position position) {
        super(island, position);
    }

    public Horse() {
        super();
    }

    public Horse(Horse horse) {
        super(horse.getIsland(), horse.getPosition());
    }

    @Override
    public IslandObject copyOf(IslandObject object) {
        return new Horse((Horse) object);
    }
}
