package com.javarush.island.bogomolov.creatures.herbivores;


import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.creatures.predators.Bear;
import com.javarush.island.bogomolov.storage.Cell;
import com.javarush.island.bogomolov.storage.IslandMap;

public class Horse extends Herbivore {
    public Horse() {
        super("Horse", 400, 4, 60, 20);
    }


    @Override
    public void spawn() {

    }
    @Override
    public void spawn(Animal couple) {


        if (couple instanceof Horse) {
            Cell cell = IslandMap.getislandMap().getCell(couple.getRow(), couple.getColumn());
            IslandMap.getislandMap().addAnimal(new Horse(), cell.getRow(), cell.getColumn());
        }
    }
}
