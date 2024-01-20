package com.javarush.island.berezovskiy.Entities.Cell;

import com.javarush.island.berezovskiy.Entities.Organism.Flock;
import com.javarush.island.berezovskiy.Entities.Organism.Organism;
import com.javarush.island.berezovskiy.Entities.Organism.TaskAnimal;
import com.javarush.island.berezovskiy.Utils.IslandModify;

import java.util.HashMap;
import java.util.Map;

public class CellWorker implements Runnable{

    public Cell getCell() {
        return cell;
    }

    private final Cell cell;
    private TaskAnimal taskAnimal;
    private HashMap<String, Flock> flocksInCell;

    public CellWorker(Cell cell) {
        this.cell = cell;
        this.flocksInCell = cell.getOrganismHashMap();
    }

    @Override
    public void run() {
        while(Organism.getOrganismNumber().get() > 0){
            work();
        }

    }

    public void work(){
            taskAnimal = new TaskAnimal(cell);
            taskAnimal.liveOrganismInCell();
            flocksInCell = taskAnimal.getOrganismHashMap();
            for (Map.Entry<String, Flock> stringFlockEntry : flocksInCell.entrySet()) {
                moveAnimalToAnotherCell(stringFlockEntry.getValue());
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
                    cell
                }
            }
        }finally {
            cell.getCellLock().unlock();
        }
    }
}
