package com.javarush.island.khasanov.entity.animals.herbivores;

import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.repository.Island;
import com.javarush.island.khasanov.repository.Position;

public class Rabbit extends Herbivore {
    public Rabbit(Island island, Position position) {
        super(island, position);
    }

    public Rabbit() {
        super();
    }

    public Rabbit(Rabbit rabbit) {
        super(rabbit.getIsland(), rabbit.getPosition());
    }

    @Override
    public IslandObject copyOf(IslandObject object) {
        return new Rabbit((Rabbit) object);
    }
}
