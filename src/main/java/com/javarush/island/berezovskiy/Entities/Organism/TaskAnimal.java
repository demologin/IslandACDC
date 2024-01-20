package com.javarush.island.berezovskiy.Entities.Organism;

import com.javarush.island.berezovskiy.Entities.Cell.Cell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskAnimal {
    private HashMap<String, Flock> organismHashMap;
    private int flocksAmountInCell;
    private Cell cell;
    List<String> organismsInMap;

    public TaskAnimal(Cell cell) {
        this.cell = cell;
    }

    public void liveOrganismInCell(){
        cell.getCellLock().lock();
        try {
            getOrganismsInMap();
            ///?????
            for (Map.Entry<String, Flock> stringFlockEntry : organismHashMap.entrySet()) {
                Flock flock = stringFlockEntry.getValue();
                flock.move(cell);
            }
        }
        finally {
            cell.getCellLock().unlock();
        }
    }
    public void setOrganismsMap(){
        organismHashMap = cell.getOrganismHashMap();
        flocksAmountInCell = organismHashMap.size();
    }

    public void getOrganismsInMap(){
        organismsInMap = new ArrayList<>(organismHashMap.keySet());
    }

    public HashMap<String, Flock> getOrganismHashMap() {
        return organismHashMap;
    }
}
