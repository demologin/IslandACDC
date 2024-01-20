package com.javarush.island.berezovskiy.Entities.Cell;

import com.javarush.island.berezovskiy.Configs.Configs;
import com.javarush.island.berezovskiy.Entities.Organism.Flock;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cell extends ConcurrentHashMap<String, Flock>{
    private final HashMap<String, Flock> organismsInCell = new HashMap<>();
    private Lock cellLock = new ReentrantLock();

    private final int coordinateX;
    private final int coordinateY;
    private boolean isCellFull = false;
    private int animalLimitInCell = Configs.ANIMAL_LIMIT_IN_CELL;
    private CellWorker cellWorker;
    private AtomicInteger flocksInCell = new AtomicInteger(0);

    public boolean isCellFull() {
        return isCellFull;
    }

    public Cell(int coordinateX, int coordinateY) {
        this.coordinateY = coordinateY;
        this.coordinateX = coordinateX;
    }
    public void putOrganism(String name, Flock organism){
        cellLock.lock();
        try {
            organismsInCell.put(name, organism);
            organism.disableAbleToMove();
            flocksInCell.incrementAndGet();
            if (flocksInCell.get() == animalLimitInCell) {
                this.isCellFull = true;
            }
        }finally {
            cellLock.unlock();
        }
    }
    public void startWorking(){
        cellWorker = new CellWorker(this);
        cellWorker.run();
    }
    public void removeOrganism(String name){
        cellLock.lock();
        try{
        organismsInCell.remove(name);
        flocksInCell.decrementAndGet();}
        finally {
            cellLock.unlock();
        }

    }
    public HashMap<String, Flock> getOrganismHashMap(){
        return organismsInCell;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }
    public Lock getCellLock() {
        return cellLock;
    }


}
