package com.javarush.island.khasanov.entity.animals.predators;

import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.repository.Island;
import com.javarush.island.khasanov.repository.Position;

public class Wolf extends Predator {
    public Wolf(Island island, Position position) {
        super(island, position);
    }

    public Wolf() {
        super();
    }

    public Wolf(Wolf wolf) {
        super(wolf.getIsland(), wolf.getPosition());
    }

    @Override
    public IslandObject copyOf(IslandObject object) {
        return new Wolf((Wolf) object);
    }
}
