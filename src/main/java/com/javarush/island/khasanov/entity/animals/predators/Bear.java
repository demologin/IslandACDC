package com.javarush.island.khasanov.entity.animals.predators;

import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.entity.Position;

public class Bear extends Predator {
    public Bear(Island island, Position position) {
        super(island, position);
    }

    public Bear() {
        super();
    }

    public Bear(Bear bear) {
        super(bear.getIsland(), bear.getPosition());
    }

    @Override
    public IslandObject copyOf(IslandObject object) {
        return new Bear((Bear) object);
    }
}
