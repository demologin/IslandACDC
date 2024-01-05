package com.javarush.island.alimova.entity.map;

import com.javarush.island.alimova.entity.alive.Organism;

import java.util.HashSet;
import java.util.Set;

public class Cell {

    private final Set<Organism> manyCreatures = new HashSet<>();
    //либо разбить организмы и растения?
    //отдавать сет для поиска нужного организма?

    public void addOrganism(Organism organism) {
        this.manyCreatures.add(organism);
    }

    public Set<Organism> getSetCreatures() {
        //не самое безопасное решение
        return manyCreatures;
    }
}
