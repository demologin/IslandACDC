package com.javarush.island.khasanov.entity.animals.predators;

import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.entity.Position;

public class Boa extends Predator {
    public Boa(Island island, Position position) {
        super(island, position);
    }

    public Boa() {
        super();
    }

    public Boa(Boa boa) {
        super(boa.getIsland(), boa.getPosition());
    }

    @Override
    public IslandObject copyOf(IslandObject object) {
        return new Boa((Boa) object);
    }
}
