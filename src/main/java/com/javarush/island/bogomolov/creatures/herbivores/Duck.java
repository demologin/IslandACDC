package com.javarush.island.bogomolov.creatures.herbivores;


import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.creatures.predators.Bear;
import com.javarush.island.bogomolov.storage.Cell;
import com.javarush.island.bogomolov.storage.IslandMap;

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

    @Override
    public void spawn() {

    }

    @Override
    public void spawn(Animal couple) {


        if (couple instanceof Duck) {
            Cell cell = IslandMap.getislandMap().getCell(couple.getRow(), couple.getColumn());
            IslandMap.getislandMap().addAnimal(new Duck(), cell.getRow(), cell.getColumn());
        }
    }
}
