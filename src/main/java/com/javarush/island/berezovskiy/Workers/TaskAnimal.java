package com.javarush.island.berezovskiy.Workers;

import com.javarush.island.berezovskiy.Entities.Organism.Flock;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaskAnimal extends ConcurrentHashMap<String, Flock> {
    private ConcurrentMap<String, Flock> organismHashMap;
    private int flocksAmountInCell;
    private CellWorker cellWorker;
    Lock taskAnimalLock = new ReentrantLock();

    List<String> organismsInMap = Collections.synchronizedList(new ArrayList<>());

    public TaskAnimal(CellWorker cellworker) {
        this.cellWorker = cellworker;
    }

    public void liveOrganismInCell() {
        moveFlock();

    }

    private void moveFlock() {
        taskAnimalLock.lock();
        try {
            this.organismHashMap = cellWorker.getCell().getOrganismHashMap();
            if (!this.organismHashMap.isEmpty()) {
                for (Map.Entry<String, Flock> stringFlockEntry : organismHashMap.entrySet()) {
                    stringFlockEntry.getValue().move(cellWorker.getCell());
                }
            }
        } finally {
            taskAnimalLock.unlock();
        }
    }
    private void eatOrganism(){
        taskAnimalLock.lock();

        taskAnimalLock.unlock();
    }

    public void setOrganismsMap() {
        taskAnimalLock.lock();
        try {
            organismHashMap = cellWorker.getCell().getOrganismHashMap();
            flocksAmountInCell = organismHashMap.size();
        } finally {
            taskAnimalLock.unlock();
        }
    }

    public ConcurrentMap<String, Flock> getOrganismHashMap() {
        return organismHashMap;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        TaskAnimal that = (TaskAnimal) object;

        if (flocksAmountInCell != that.flocksAmountInCell) return false;
        if (!Objects.equals(organismHashMap, that.organismHashMap))
            return false;
        if (!Objects.equals(cellWorker, that.cellWorker)) return false;
        if (!Objects.equals(taskAnimalLock, that.taskAnimalLock))
            return false;
        return Objects.equals(organismsInMap, that.organismsInMap);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (organismHashMap != null ? organismHashMap.hashCode() : 0);
        result = 31 * result + flocksAmountInCell;
        result = 31 * result + (cellWorker != null ? cellWorker.hashCode() : 0);
        result = 31 * result + (taskAnimalLock != null ? taskAnimalLock.hashCode() : 0);
        result = 31 * result + (organismsInMap != null ? organismsInMap.hashCode() : 0);
        return result;
    }
}
