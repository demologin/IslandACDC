package com.javarush.island.bogomolov.creatures.predators;

import com.javarush.island.bogomolov.Limit;
import com.javarush.island.bogomolov.api.annotation.CreatureAnnotation;
import com.javarush.island.bogomolov.storage.Cell;


public class Bear extends Predator {
    public Bear() {
        super("Bear", 500, 2, 80, 5);
    }


    @Override
    public double getChancesToEat(String targetName) {
        return switch (targetName) {
            case "Snake", "Deer", "Rabbit" -> 0.8;
            case "Goat", "Sheep" -> 0.7;
            case "Boar" -> 0.5;
            case "Horse" -> 0.4;
            case "Duck" -> 0.1;
            case "Buffalo" -> 0.2;
            default -> 0;

        };

    }
}


