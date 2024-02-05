package com.javarush.island.berezovskiy.entities.cell;
import com.javarush.island.berezovskiy.configs.Configs;
import com.javarush.island.berezovskiy.entities.organism.Flock;
import com.javarush.island.berezovskiy.workers.CellWorker;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cell extends ConcurrentHashMap<String, Flock>{
    private final ConcurrentMap<String, Flock> organismsInCell = new ConcurrentHashMap<>();
    private final Lock cellLock = new ReentrantLock();
    private final int coordinateX;
    private final int coordinateY;
    private boolean cellFull = false;
    private final AtomicInteger flocksInCell = new AtomicInteger(0);
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
    public boolean isCellFull() {
        return cellFull;
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
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        Cell cell = (Cell) object;

        if (coordinateX != cell.coordinateX) return false;
        if (coordinateY != cell.coordinateY) return false;
        if (cellFull != cell.cellFull) return false;
        if (!organismsInCell.equals(cell.organismsInCell)) return false;
        if (!cellLock.equals(cell.cellLock)) return false;
        return flocksInCell.get() == cell.flocksInCell.get();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + organismsInCell.hashCode();
        result = 31 * result + cellLock.hashCode();
        result = 31 * result + coordinateX;
        result = 31 * result + coordinateY;
        result = 31 * result + (cellFull ? 1 : 0);
        result = 31 * result + flocksInCell.hashCode();
        return result;
    }
}
