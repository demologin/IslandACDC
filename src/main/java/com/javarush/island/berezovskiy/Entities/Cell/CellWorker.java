package com.javarush.island.berezovskiy.Entities.Cell;

import com.javarush.island.berezovskiy.Entities.Organism.OrganismsSet;
import com.javarush.island.berezovskiy.Entities.Organism.TaskAnimal;

import java.util.HashMap;
import java.util.Map;

public class CellWorker {

    private final Cell cell;
    private TaskAnimal taskAnimal;
    private final HashMap<String, OrganismsSet> cellHashMap;

    public CellWorker(Cell cell, HashMap<String, OrganismsSet> cellHashMap) {
        this.cell = cell;
        this.cellHashMap = cellHashMap;
    }

    public void work(){
        for (Map.Entry<String, OrganismsSet> stringOrganismsSetEntry : cellHashMap.entrySet()) {

        }
    }
}
