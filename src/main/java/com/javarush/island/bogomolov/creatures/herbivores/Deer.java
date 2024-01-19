package com.javarush.island.bogomolov.creatures.herbivores;


import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.creatures.predators.Bear;
import com.javarush.island.bogomolov.storage.Cell;
import com.javarush.island.bogomolov.storage.IslandMap;

public class Deer extends Herbivore{
    public Deer() {
        super("Deer",300,4,50,20);
    }


    @Override
    public void spawn() {

    }

    @Override
    public void spawn(Animal couple) {


        if (couple instanceof Deer) {
            Cell cell = IslandMap.getislandMap().getCell(couple.getRow(), couple.getColumn());
            IslandMap.getislandMap().addAnimal(new Deer(), cell.getRow(), cell.getColumn());
        }
    }
}
