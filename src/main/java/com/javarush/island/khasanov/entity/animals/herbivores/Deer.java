package com.javarush.island.khasanov.entity.animals.herbivores;

import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.entity.Position;

public class Deer extends Herbivore {
    public Deer(Island island, Position position) {
        super(island, position);
    }

    public Deer() {
        super();
    }

    public Deer(Deer deer) {
        super(deer.getIsland(), deer.getPosition());
    }

    @Override
    public IslandObject copyOf(IslandObject object) {
        return new Deer((Deer) object);
    }
}
