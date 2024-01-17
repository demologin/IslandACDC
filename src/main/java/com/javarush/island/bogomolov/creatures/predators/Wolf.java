package com.javarush.island.bogomolov.creatures.predators;

import com.javarush.island.bogomolov.Limit;
import com.javarush.island.bogomolov.storage.Cell;


public class Wolf extends Predator {
    public Wolf() {
        super("Wolf", 50, 3, 8, 30);
    }


    @Override
    public double getChancesToEat(String targetName) {

        return switch (targetName) {
            case "Horse", "Buffalo" -> 0.1;
            case "Deer", "Boar" -> 0.15;
            case "Rabbit", "Goat" -> 0.6;
            case "Mouse" -> 0.8;
            case "Sheep" -> 0.7;
            case "Duck" -> 0.4;
            default -> 0;
        };
    }


}
