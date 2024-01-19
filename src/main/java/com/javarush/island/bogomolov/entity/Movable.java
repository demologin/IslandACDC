package com.javarush.island.bogomolov.entity;

import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.storage.Cell;

public interface Movable {
    boolean move(Animal animal);
}
