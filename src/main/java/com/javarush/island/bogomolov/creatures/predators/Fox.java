package com.javarush.island.bogomolov.creatures.predators;


import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.storage.Cell;
import com.javarush.island.bogomolov.storage.IslandMap;

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

    @Override
    public void spawn() {

    }

    @Override
    public void spawn(Animal couple) {


        if (couple instanceof Fox) {
            Cell cell = IslandMap.getislandMap().getCell(couple.getRow(), couple.getColumn());
            IslandMap.getislandMap().addAnimal(new Fox(), cell.getRow(), cell.getColumn());
        }
    }
}
