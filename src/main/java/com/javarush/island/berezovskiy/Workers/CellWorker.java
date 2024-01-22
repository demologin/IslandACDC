package com.javarush.island.berezovskiy.Workers;

import com.javarush.island.berezovskiy.Configs.Configs;
import com.javarush.island.berezovskiy.Entities.Cell.Cell;
import com.javarush.island.berezovskiy.Entities.Organism.Flock;
import com.javarush.island.berezovskiy.Entities.Organism.Organism;
import com.javarush.island.berezovskiy.Utils.IslandModify;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CellWorker extends ConcurrentHashMap<String,Flock> implements Runnable{

    public Cell getCell() {
        return cell;
    }

    private final Cell cell;
    private TaskAnimal taskAnimal;
    private ConcurrentMap<String, Flock> flocksInCell;

    public CellWorker(Cell cell) {
        this.cell = cell;
    }

    @Override
    public void run() {
        while(Organism.getOrganismNumber().get() > 0){
            work();
            try {
                Thread.sleep(Configs.TIME_FOR_WAITING*1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void work() {
        this.flocksInCell = cell.getOrganismHashMap();
            setTaskAnimal();
        System.out.println(Thread.currentThread() + " I am running");
            taskAnimal.liveOrganismInCell();
            this.flocksInCell = taskAnimal.getOrganismHashMap();
            for (Map.Entry<String, Flock> stringFlockEntry : flocksInCell.entrySet()) {
                moveAnimalToAnotherCell(stringFlockEntry.getValue());
            }

    }

    public void setTaskAnimal(){
        cell.getCellLock().lock();
        try{
            taskAnimal = new TaskAnimal(this);
        }
        finally {
            cell.getCellLock().unlock();
        }
    }

    public void moveAnimalToAnotherCell(Flock flock) {
        cell.getCellLock().lock();
        try {
            if (flock.isAbleToMove()) {
                int[] coordinates = flock.getCoordinatesToMove();
                int coordinateXCellToMove = coordinates[0];
                int coordinateYCellToMove = coordinates[1];
                Cell cellToMove = IslandModify.getCurrentIsland().getIsland()[coordinateXCellToMove][coordinateYCellToMove];
                if (!cellToMove.isCellFull()) {
                    cellToMove.putOrganism(flock.getOrganism().getName(),flock);
                    cell.removeOrganism(flock.getOrganism().getName());
                }
                else{
                    flock.disableAbleToMove();
                }
            }
        } finally {
            cell.getCellLock().unlock();
        }
    }
}
