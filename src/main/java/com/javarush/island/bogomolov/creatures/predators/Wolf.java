package com.javarush.island.bogomolov.creatures.predators;

import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.storage.Cell;
import com.javarush.island.bogomolov.storage.IslandMap;


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

    @Override
    public void spawn() {

    }

    @Override
    public void spawn(Animal couple) {


        if (couple instanceof Wolf) {
            Cell cell = IslandMap.getislandMap().getCell(couple.getRow(), couple.getColumn());
            IslandMap.getislandMap().addAnimal(new Wolf(), cell.getRow(), cell.getColumn());
        }
    }


}
