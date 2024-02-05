package com.javarush.island.khasanov.entity.animals.herbivores;

import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.entity.Position;

public class Mouse extends Herbivore {
    public Mouse(Island island, Position position) {
        super(island, position);
    }

    public Mouse() {
        super();
    }

    public Mouse(Mouse mouse) {
        super(mouse.getIsland(), mouse.getPosition());
    }

    @Override
    public IslandObject copyOf(IslandObject object) {
        return new Mouse((Mouse) object);
    }
}
