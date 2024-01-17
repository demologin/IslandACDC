package com.javarush.island.bogomolov.creatures.predators;

import com.javarush.island.bogomolov.Limit;
import com.javarush.island.bogomolov.api.annotation.CreatureAnnotation;
import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.storage.Cell;


public class Snake extends Predator {
    public Snake() {
        super("Snake", 15, 1, 3, 30);
    }

    @Override
    public double getChancesToEat(String targetName) {
        return switch (targetName) {
            case "Fox" -> 0.15;
            case "Rabbit" -> 0.2;
            case "Mouse" -> 0.4;
            case "Duck" -> 0.1;
            default -> 0;
        };
    }
}



