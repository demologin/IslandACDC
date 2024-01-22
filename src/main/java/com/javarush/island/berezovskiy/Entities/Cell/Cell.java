package com.javarush.island.berezovskiy.Entities.Cell;

import com.javarush.island.berezovskiy.Configs.Configs;
import com.javarush.island.berezovskiy.Entities.Organism.Flock;
import com.javarush.island.berezovskiy.Workers.CellWorker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cell extends ConcurrentHashMap<String, Flock>{
    private final ConcurrentMap<String, Flock> organismsInCell = new ConcurrentHashMap<>();
    private Lock cellLock = new ReentrantLock();

    private final int coordinateX;
    private final int coordinateY;
    private boolean cellFull = false;
    private CellWorker cellWorker;
    private AtomicInteger flocksInCell = new AtomicInteger(0);

    public boolean isCellFull() {
        return cellFull;
    }
    public void setCellFull(boolean cellFull) {
        this.cellFull = cellFull;
    }

    public Cell(int coordinateX, int coordinateY) {
        this.coordinateY = coordinateY;
        this.coordinateX = coordinateX;
    }
    public void putOrganism(String name, Flock organism){
        cellLock.lock();
        try {
            this.organismsInCell.put(name, organism);
            organism.disableAbleToMove();
            this.flocksInCell.incrementAndGet();
            if (this.flocksInCell.get() == Configs.ANIMAL_LIMIT_IN_CELL) {
                this.cellFull = true;
            }
        }finally {
            cellLock.unlock();
        }
    }
    public void removeOrganism(String name){
        cellLock.lock();
        try{
        this.organismsInCell.remove(name);
        this.flocksInCell.decrementAndGet();}
        finally {
            cellLock.unlock();
        }
    }

    public CellWorker startWorking(){
        return new CellWorker(this);
    }
    public ConcurrentMap<String, Flock> getOrganismHashMap(){
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
    public CellWorker getCellWorker() {
        return cellWorker;
    }

    public boolean isCellEmpty(){
        return organismsInCell.isEmpty();
    }


}
