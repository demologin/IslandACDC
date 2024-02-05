package com.javarush.island.khasanov.entity.animals.herbivores;

import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.entity.Position;

public class Caterpillar extends Herbivore {
    public Caterpillar(Island island, Position position) {
        super(island, position);
    }

    public Caterpillar() {
        super();
    }

    public Caterpillar(Caterpillar caterpillar) {
        super(caterpillar.getIsland(), caterpillar.getPosition());
    }

    @Override
    public IslandObject copyOf(IslandObject object) {
        return new Caterpillar((Caterpillar) object);
    }
}
