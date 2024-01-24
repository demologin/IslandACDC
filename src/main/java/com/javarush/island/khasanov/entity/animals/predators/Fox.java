package com.javarush.island.khasanov.entity.animals.predators;

import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.entity.Position;

public class Fox extends Predator {
    public Fox(Island island, Position position) {
        super(island, position);
    }

    public Fox() {
        super();
    }

    public Fox(Fox fox) {
        super(fox.getIsland(), fox.getPosition());
    }

    @Override
    public IslandObject copyOf(IslandObject object) {
        return new Fox((Fox) object);
    }
}
