package com.javarush.island.khasanov.entity.plants;

import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.entity.Position;

public class Grass extends Plant {
    public Grass(Island island, Position position) {
        super(island, position);
    }

    public Grass() {
        super();
    }

    @Override
    public IslandObject copyOf(IslandObject object) {
        return null;
    }
}
