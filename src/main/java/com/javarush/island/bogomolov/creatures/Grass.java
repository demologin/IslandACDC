package com.javarush.island.bogomolov.creatures;

import com.javarush.island.bogomolov.Limit;
import com.javarush.island.bogomolov.api.annotation.CreatureAnnotation;
import com.javarush.island.bogomolov.storage.Cell;


public class Grass extends Plant {
    public Grass() {
        super("Grass", 1, 200);
    }


    @Override
    public boolean spawn(Cell cell) {
        return false;
    }
}
