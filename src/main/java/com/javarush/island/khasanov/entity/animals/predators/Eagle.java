package com.javarush.island.khasanov.entity.animals.predators;

import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.entity.Position;

public class Eagle extends Predator {
    public Eagle(Island island, Position position) {
        super(island, position);
    }

    public Eagle() {
        super();
    }

    public Eagle(Eagle eagle) {
        super(eagle.getIsland(), eagle.getPosition());
    }

    @Override
    public IslandObject copyOf(IslandObject object) {
        return new Eagle((Eagle) object);
    }
}
