package com.javarush.island.bogomolov.entity;

import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.creatures.Organism;
import com.javarush.island.bogomolov.storage.Cell;

public interface Reproducible {
    void spawn();

    void spawn(Animal couple);
}
