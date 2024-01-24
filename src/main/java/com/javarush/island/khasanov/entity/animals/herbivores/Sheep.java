package com.javarush.island.khasanov.entity.animals.herbivores;

import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.entity.Position;

public class Sheep extends Herbivore {
    public Sheep(Island island, Position position) {
        super(island, position);
    }

    public Sheep() {
        super();
    }

    public Sheep(Sheep sheep) {
        super(sheep.getIsland(), sheep.getPosition());
    }

    @Override
    public IslandObject copyOf(IslandObject object) {
        return new Sheep((Sheep) object);
    }
}
