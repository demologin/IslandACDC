package com.javarush.island.khasanov.entity.animals.herbivores;

import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.entity.Position;

public class Buffalo extends Herbivore {
    public Buffalo(Island island, Position position) {
        super(island, position);
    }

    public Buffalo() {
        super();
    }

    public Buffalo(Buffalo wildBoar) {
        super(wildBoar.getIsland(), wildBoar.getPosition());
    }

    @Override
    public IslandObject copyOf(IslandObject object) {
        return new Buffalo((Buffalo) object);
    }
}
