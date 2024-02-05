package com.javarush.island.khasanov.entity.animals.herbivores;

import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.entity.Position;

public class Duck extends Herbivore {
    public Duck(Island island, Position position) {
        super(island, position);
    }

    public Duck() {
        super();
    }

    public Duck(Duck duck) {
        super(duck.getIsland(), duck.getPosition());
    }

    @Override
    public IslandObject copyOf(IslandObject object) {
        return new Duck((Duck) object);
    }
}
