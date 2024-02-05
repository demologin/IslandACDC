package com.javarush.island.bogomolov.creatures.herbivores;


import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.creatures.predators.Bear;
import com.javarush.island.bogomolov.storage.Cell;
import com.javarush.island.bogomolov.storage.IslandMap;

public class Mouse extends Herbivore {
    public Mouse() {
        super("Mouse", 0.05, 1, 0.01, 500);
    }


    @Override
    public void spawn() {

    }
    @Override
    public void spawn(Animal couple) {


        if (couple instanceof Mouse) {
            Cell cell = IslandMap.getislandMap().getCell(couple.getRow(), couple.getColumn());
            IslandMap.getislandMap().addAnimal(new Mouse(), cell.getRow(), cell.getColumn());
        }
    }
}
