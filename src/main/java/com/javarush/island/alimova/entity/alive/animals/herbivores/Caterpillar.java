package com.javarush.island.alimova.entity.alive.animals.herbivores;

import com.javarush.island.alimova.configure.DefaultSettings;
import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.entity.map.Cell;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class Caterpillar extends Herbivore{

    public Caterpillar(double weight, int maxAmount, int maxSpeed, double maxFoodWeight, boolean satiety) {
        super(weight, maxAmount, maxSpeed, maxFoodWeight, true);
    }

    @Override
    public Organism clone() throws CloneNotSupportedException {
        return new Caterpillar(this.weight, this.maxAmount, this.maxSpeed, this.maxFoodWeight, this.satiety);
    }

    @Override
    public void multiply(Cell currentCell) {
        long amountGrass = currentCell.checkAmountOrganism(this.getClass().getSimpleName());
        if (amountGrass < this.maxAmount) {
            try {
                Organism newOrganism = this.clone();
                currentCell.addOrganismToQueueWithStatistic(newOrganism);
            } catch (CloneNotSupportedException e) {
                System.err.println(DefaultSettings.MESSAGE_FATAL_ERROR);
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void checkHungry(double currentEatenMass, Cell currentCell) {}

    @Override
    public String toString() {
        return "Caterpillar";
    }
}
