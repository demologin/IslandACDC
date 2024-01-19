package com.javarush.island.bogomolov.creatures.herbivores;


import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.creatures.predators.Bear;
import com.javarush.island.bogomolov.storage.Cell;
import com.javarush.island.bogomolov.storage.IslandMap;

public class Boar extends Herbivore {
    public Boar() {
        super("Boar", 400, 2, 50, 50);
    }


    @Override
    public void spawn() {

    }

    @Override
    public void spawn(Animal couple) {


        if (couple instanceof Boar) {
            Cell cell = IslandMap.getislandMap().getCell(couple.getRow(), couple.getColumn());
            IslandMap.getislandMap().addAnimal(new Boar(), cell.getRow(), cell.getColumn());
        }
    }
}
