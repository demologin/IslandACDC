package com.javarush.island.bogomolov.entity;

import com.javarush.island.bogomolov.storage.Cell;

public interface Movable {
    boolean move(Cell startcell);
}
