package com.javarush.island.khasanov.entity.animals.herbivores;

import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.entity.Position;

public class Goat extends Herbivore {
    public Goat(Island island, Position position) {
        super(island, position);
    }

    public Goat() {
        super();
    }

    public Goat(Goat goat) {
        super(goat.getIsland(), goat.getPosition());
    }

    @Override
    public IslandObject copyOf(IslandObject object) {
        return new Goat((Goat) object);
    }
}
