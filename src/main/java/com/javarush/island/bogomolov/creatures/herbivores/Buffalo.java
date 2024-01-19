package com.javarush.island.bogomolov.creatures.herbivores;


import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.creatures.predators.Bear;
import com.javarush.island.bogomolov.storage.Cell;
import com.javarush.island.bogomolov.storage.IslandMap;

public class Buffalo extends Herbivore {
    public Buffalo() {
        super("Buffalo", 700, 3, 100, 10);
    }


    @Override
    public void spawn() {

    }

    @Override
    public void spawn(Animal couple) {


        if (couple instanceof Buffalo) {
            Cell cell = IslandMap.getislandMap().getCell(couple.getRow(), couple.getColumn());
            IslandMap.getislandMap().addAnimal(new Buffalo(), cell.getRow(), cell.getColumn());
        }
    }
}
