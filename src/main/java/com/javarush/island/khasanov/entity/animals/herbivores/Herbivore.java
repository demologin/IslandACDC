package com.javarush.island.khasanov.entity.animals.herbivores;

import com.javarush.island.khasanov.entity.animals.Animal;
import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.repository.Position;

import java.util.HashMap;

public abstract class Herbivore extends Animal {
    protected Herbivore(Island island, Position position) {
        super(island, position);
        addFood(new HashMap<>(){{
            put("Grass", 100);
        }});
    }

    protected Herbivore() {
        super();
    }

}
