package com.javarush.island.khasanov.entity.animals.herbivores;

import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.entity.Position;

public class Hog extends Herbivore {
    public Hog(Island island, Position position) {
        super(island, position);
    }

    public Hog() {
        super();
    }

    public Hog(Hog hog) {
        super(hog.getIsland(), hog.getPosition());
    }

    @Override
    public IslandObject copyOf(IslandObject object) {
        return new Hog((Hog) object);
    }
}
