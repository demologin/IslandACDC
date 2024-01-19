package com.javarush.island.bogomolov.creatures.herbivores;


import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.creatures.predators.Bear;
import com.javarush.island.bogomolov.storage.Cell;
import com.javarush.island.bogomolov.storage.IslandMap;

public class Rabbit extends Herbivore {
    public Rabbit() {
        super("Rabbit", 2, 2, 0.45, 150);
    }


    @Override
    public void spawn() {

    }
    @Override
    public void spawn(Animal couple) {


        if (couple instanceof Rabbit) {
            Cell cell = IslandMap.getislandMap().getCell(couple.getRow(), couple.getColumn());
            IslandMap.getislandMap().addAnimal(new Rabbit(), cell.getRow(), cell.getColumn());
        }
    }
}
