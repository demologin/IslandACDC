package com.javarush.island.alimova.entity.alive.plants;

import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.entity.map.Cell;

public abstract class Plant extends Organism {

    public Plant(double weight, int maxAmount) {
        super(weight, maxAmount);
    }

    @Override
    public void multiply(Cell currentCell) {
        long amountGrass = currentCell.checkAmountOrganism(this.getClass().getSimpleName());
        if (amountGrass < this.maxAmount) {
            try {
                Organism newGrass = this.clone();
                currentCell.addOrganismToQueueWithStatistic(newGrass);
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
