package com.javarush.island.berezovskiy.Entities.Cell;

import com.javarush.island.berezovskiy.Entities.Organism.Organism;

import java.util.HashMap;

public class Cell implements Runnable{
    private HashMap<Organism, Integer> organismIntegerHashMap = new HashMap<>();
    private final int coordinateX;
    private final int coordinateY;
    public Cell(int coordinateX,int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public void putOrganism(Organism organism, Integer value){
        organismIntegerHashMap.put(organism, value);
    }

    public HashMap<Organism, Integer> getOrganismIntegerHashMap(){
        return organismIntegerHashMap;
    }

    @Override
    public void run() {

    }
}
