package com.javarush.island.bogomolov.creatures.herbivores;


import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.creatures.predators.Bear;
import com.javarush.island.bogomolov.storage.Cell;
import com.javarush.island.bogomolov.storage.IslandMap;

public class Goat extends Herbivore {
    public Goat() {
        super("Goat", 60, 3, 10, 140);
    }

    @Override
    public void spawn() {

    }

    @Override
    public void spawn(Animal couple) {


        if (couple instanceof Goat) {
            Cell cell = IslandMap.getislandMap().getCell(couple.getRow(), couple.getColumn());
            IslandMap.getislandMap().addAnimal(new Goat(), cell.getRow(), cell.getColumn());
        }
    }
}
