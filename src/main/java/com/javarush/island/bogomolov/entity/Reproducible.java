package com.javarush.island.bogomolov.entity;

import com.javarush.island.bogomolov.storage.Cell;

public interface Reproducible {
    boolean spawn(Cell cell);
}
