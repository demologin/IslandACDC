package com.javarush.island.alimova.entity.alive.plants;

import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.entity.map.Cell;

public class Grass extends Plant{

    public Grass(double weight, int maxAmount) {
        super(weight, maxAmount);
    }

    @Override
    public Organism clone() throws CloneNotSupportedException {
        return new Grass(this.weight, this.maxAmount);
    }



    @Override
    public void multiply(Cell currentCell) {
        long amountGrass = currentCell.checkAmountOrganism(this.getClass().getSimpleName());  //может быть хранить где-то имя
        if (amountGrass < this.maxAmount) {
            try {
                Organism newGrass = this.clone();
                currentCell.addOrganismToQueue(newGrass);
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        } else {
            //todo //кинуть какое-то исключение? либо возвращать булеан
        }
    }



    @Override
    public String toString() {
        return "__Grass__";
    }
}
