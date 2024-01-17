package com.javarush.island.bogomolov.creatures.predators;

import com.javarush.island.bogomolov.Limit;
import com.javarush.island.bogomolov.api.annotation.CreatureAnnotation;
import com.javarush.island.bogomolov.storage.Cell;


public class Fox extends Predator {
    public Fox() {
        super("Fox", 8, 2, 2, 30);
    }


    @Override
    public double getChancesToEat(String targetName) {
        return switch (targetName) {
            case "Rabbit" -> 0.7;
            case "Mouse" -> 0.9;
            case "Duck" -> 0.6;
            case "Caterpillar" -> 0.4;
            default -> 0;
        };
    }
}
