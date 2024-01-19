package com.javarush.island.bogomolov.creatures.predators;

import com.javarush.island.bogomolov.Limit;
import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.storage.Cell;
import com.javarush.island.bogomolov.storage.IslandMap;


public class Eagle extends Predator {
    public Eagle() {
        super("Eagle", 6, 3, 1, 20);
    }


    @Override
    public double getChancesToEat(String targetName) {
        return switch (targetName) {
            case "Fox" -> 0.1;
            case "Rabbit", "Mouse" -> 0.9;
            case "Duck" -> 0.8;
            default -> 0;
        };
    }

    @Override
    public void spawn() {

    }

    @Override
    public void spawn(Animal couple) {


        if (couple instanceof Eagle) {
            Cell cell = IslandMap.getislandMap().getCell(couple.getRow(), couple.getColumn());
            IslandMap.getislandMap().addAnimal(new Eagle(), cell.getRow(), cell.getColumn());
        }
    }
}
