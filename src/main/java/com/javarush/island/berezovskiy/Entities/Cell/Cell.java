package com.javarush.island.berezovskiy.Entities.Cell;

import com.javarush.island.berezovskiy.Entities.Organism.Organism;
import com.javarush.island.berezovskiy.Entities.Organism.OrganismsSet;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Cell extends ConcurrentHashMap<String, OrganismsSet>implements Runnable{
    private HashMap<String, OrganismsSet> organismIntegerHashMap = new HashMap<>();

    private final int coordinateX;
    private final int coordinateY;
    public Cell(int coordinateX,int coordinateY) {
        this.coordinateY = coordinateY;
        this.coordinateX = coordinateX;
    }
    @Override
    public void run() {

    }

    public void putOrganism(String name, OrganismsSet organism){
        organismIntegerHashMap.put(name, organism);
    }
    public void removeOrganism(String name){
        organismIntegerHashMap.remove(name);
    }


    public HashMap<String, OrganismsSet> getOrganismHashMap(){
        return organismIntegerHashMap;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }


}
