package com.javarush.island.bogomolov.creatures.predators;


import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.creatures.Organism;
import com.javarush.island.bogomolov.storage.Cell;
import com.javarush.island.bogomolov.storage.IslandMap;

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

    @Override
    public void spawn(Animal couple) {


        if (couple instanceof Bear) {
            Cell cell = IslandMap.getislandMap().getCell(couple.getRow(), couple.getColumn());
            IslandMap.getislandMap().addAnimal(new Bear(), cell.getRow(), cell.getColumn());
        }
    }


    @Override
    public void spawn() {

    }


}



