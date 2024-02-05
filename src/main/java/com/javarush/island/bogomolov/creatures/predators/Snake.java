package com.javarush.island.bogomolov.creatures.predators;


import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.storage.Cell;
import com.javarush.island.bogomolov.storage.IslandMap;

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

    @Override
    public void spawn() {

    }

    @Override
    public void spawn(Animal couple) {


        if (couple instanceof Snake) {
            Cell cell = IslandMap.getislandMap().getCell(couple.getRow(), couple.getColumn());
            IslandMap.getislandMap().addAnimal(new Snake(), cell.getRow(), cell.getColumn());
        }
    }
}



