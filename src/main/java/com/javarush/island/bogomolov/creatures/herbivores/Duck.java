package com.javarush.island.bogomolov.creatures.herbivores;

import com.javarush.island.bogomolov.Limit;
import com.javarush.island.bogomolov.api.annotation.CreatureAnnotation;
import com.javarush.island.bogomolov.storage.Cell;


public class Duck extends Herbivore {
    public Duck() {
        super("Duck", 1, 4, 0.15, 200);
    }


    @Override
    public double getChancesToEat(String targetName) {
        return switch (targetName) {
            case "Grass" -> 1;
            case "Caterpillar" -> 0.9;
            default -> 0;
        };
    }
}
