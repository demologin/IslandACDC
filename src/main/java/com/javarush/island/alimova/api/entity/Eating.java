package com.javarush.island.alimova.api.entity;

import com.javarush.island.alimova.configure.SettingsEntity;
import com.javarush.island.alimova.entity.map.Cell;

public interface Eating {

    void eat(Cell currentCell, SettingsEntity settings);
}
