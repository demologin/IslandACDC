package com.javarush.island.khasanov.entity.plants;

import com.javarush.island.khasanov.config.Setting;
import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.repository.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Setter
@Getter
public abstract class Plant extends IslandObject {
    protected Plant(Island island, Position position) {
        super(island, position);
        Setting.loadIslandObject(this);
    }

    public Plant() {
        super();
    }

    @Override
    public List<IslandObject> eat() {
        return Collections.emptyList();
    }

    @Override
    public Position move() {
        return position;
    }

    @Override
    public IslandObject reproduce() {
        return this;
    }

    @Override
    public void strave() {

    }

    @Override
    public void saturate(double weight) {

    }
}
