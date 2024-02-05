package com.javarush.island.bogomolov.creatures.herbivores;


import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.creatures.predators.Bear;
import com.javarush.island.bogomolov.storage.Cell;
import com.javarush.island.bogomolov.storage.IslandMap;

public class Sheep extends Herbivore {
    public Sheep() {
        super("Sheep", 70, 3, 15, 140);
    }


    @Override
    public void spawn() {

    }
    @Override
    public void spawn(Animal couple) {


        if (couple instanceof Sheep) {
            Cell cell = IslandMap.getislandMap().getCell(couple.getRow(), couple.getColumn());
            IslandMap.getislandMap().addAnimal(new Sheep(), cell.getRow(), cell.getColumn());
        }
    }
}
