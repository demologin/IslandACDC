package com.javarush.island.berezovskiy.workers;

import com.javarush.island.berezovskiy.constants.Constants;
import com.javarush.island.berezovskiy.entities.organism.animals.Animal;
import com.javarush.island.berezovskiy.entities.organism.Flock;
import com.javarush.island.berezovskiy.entities.organism.Organism;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaskAnimal extends ConcurrentHashMap<String, Flock> {
    private ConcurrentMap<String, Flock> taskOrganismHashMap;
    private int flocksAmountInCell;
    private CellWorker cellWorker;
    private Lock taskAnimalLock = new ReentrantLock();
    HashMap<String, Integer> flocksAmountInMap = new HashMap<>();

    public TaskAnimal(CellWorker cellworker) {
        this.cellWorker = cellworker;
    }

    public void liveOrganismInCell() {
        setOrganismsMap();
        eat();
        reproduce();
        moveFlock();
    }

    private void moveFlock() {
        taskAnimalLock.lock();
        try {
            if (!this.taskOrganismHashMap.isEmpty()) {
                for (Map.Entry<String, Flock> flock : taskOrganismHashMap.entrySet()) {
                    flock.getValue().move(cellWorker.getCell());
                }
            }
        } finally {
            taskAnimalLock.unlock();
        }
    }

    private void eat() {
        if (flocksAmountInCell > 1) {
            tryToEat();
            deleteDeadOrganisms();
            deleteEmptyFlocks();
        }
    }

    private void reproduce() {
        taskAnimalLock.lock();
        try {
            for (Entry<String, Flock> stringFlockEntry : taskOrganismHashMap.entrySet()) {
                Flock flock = stringFlockEntry.getValue();
                if (!flock.getOrganismType().equalsIgnoreCase(Constants.PLANT)) {
                    for (Organism organism : flock.getOrganisms()) {
                        if (!organism.isNotReadyToGiveBirth()) {
                            Animal animal = (Animal) organism;
                            Optional<Organism> organismChild = animal.getOrganismChild(animal);
                            organism.setNotReadyToGiveBirth(true);
                            if (organismChild.isPresent()){
                                if(flock.getOrganismCount() < flock.getMaximumOrganismsInFlock()) {
                                    flock.addNewOrganismChild(organismChild.get());
                            }
                                else {
                                    organismChild.get().decrementOrganismCount();
                                }
                            }
                        }
                        organism.setStarved(true);
                    }
                }
            }
        }
        finally {
            taskAnimalLock.unlock();
        }
    }

    private void deleteEmptyFlocks() {
        taskAnimalLock.lock();
        try {
            for (Iterator<Entry<String, Flock>> iterator = taskOrganismHashMap.entrySet().iterator(); iterator.hasNext(); ) {
                Entry<String, Flock> stringFlockEntry = iterator.next();
                Flock currentFlock = stringFlockEntry.getValue();
                if (currentFlock.getOrganisms().isEmpty()) {
                    iterator.remove();
                }
            }
        } finally {
            taskAnimalLock.unlock();
        }
    }

    private void deleteDeadOrganisms() {
        taskAnimalLock.lock();
        try {
            for (Flock flock : taskOrganismHashMap.values()) {
                Set<Organism> organismsInFlock = flock.getOrganisms();
                for (Iterator<Organism> iterator = organismsInFlock.iterator(); iterator.hasNext(); ) {
                    Organism organism = iterator.next();
                    if (!organism.isAlive()) {
                        organism.decrementOrganismCount();
                        iterator.remove();
                    }
                }
            }
        } finally {
            taskAnimalLock.unlock();
        }
    }

    private void tryToEat() {
        taskAnimalLock.lock();
        try {
            for (Flock flock : taskOrganismHashMap.values()) {
                Set<Organism> organismsInFlock = flock.getOrganisms();
                for (Organism organism : organismsInFlock) {
                    for (Flock flockForFood : taskOrganismHashMap.values()) {
                        Set<Organism> setOrganismsForFood = flockForFood.getOrganisms();
                        for (Organism organismForFood : setOrganismsForFood) {
                            if (organism instanceof Animal animal && organism.isStarved()) {
                                animal.eat(organismForFood);
                            }
                        }
                    }
                }
            }
        } finally {
            taskAnimalLock.unlock();
        }
    }

    public void setOrganismsMap() {
        taskAnimalLock.lock();
        try {
            taskOrganismHashMap = cellWorker.getCell().getOrganismHashMap();
            flocksAmountInCell = taskOrganismHashMap.size();
        } finally {
            taskAnimalLock.unlock();
        }
    }

    public ConcurrentMap<String, Flock> getTaskOrganismHashMap() {
        return taskOrganismHashMap;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        TaskAnimal that = (TaskAnimal) object;

        if (flocksAmountInCell != that.flocksAmountInCell) return false;
        if (!Objects.equals(taskOrganismHashMap, that.taskOrganismHashMap))
            return false;
        if (!Objects.equals(cellWorker, that.cellWorker)) return false;
        if (!Objects.equals(taskAnimalLock, that.taskAnimalLock))
            return false;
        return Objects.equals(flocksAmountInMap, that.flocksAmountInMap);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (taskOrganismHashMap != null ? taskOrganismHashMap.hashCode() : 0);
        result = 31 * result + flocksAmountInCell;
        result = 31 * result + (cellWorker != null ? cellWorker.hashCode() : 0);
        result = 31 * result + (taskAnimalLock != null ? taskAnimalLock.hashCode() : 0);
        result = 31 * result + (flocksAmountInMap != null ? flocksAmountInMap.hashCode() : 0);
        return result;
    }
}
