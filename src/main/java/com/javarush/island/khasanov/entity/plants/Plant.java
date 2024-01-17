package com.javarush.island.khasanov.entity.plants;

import com.javarush.island.khasanov.config.Setting;
import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.repository.*;
import lombok.Getter;
import lombok.Setter;

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
}
