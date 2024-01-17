package com.javarush.island.bogomolov.creatures.herbivores;

import com.javarush.island.bogomolov.Limit;
import com.javarush.island.bogomolov.api.annotation.CreatureAnnotation;
import com.javarush.island.bogomolov.storage.Cell;


public class Mouse extends Herbivore {
    public Mouse() {
        super("Mouse", 0.05, 1, 0.01, 500);
    }


}
