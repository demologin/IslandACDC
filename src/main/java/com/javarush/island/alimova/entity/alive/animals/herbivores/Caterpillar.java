package com.javarush.island.alimova.entity.alive.animals.herbivores;

import com.javarush.island.alimova.configure.DefaultSettings;
import com.javarush.island.alimova.configure.SettingsEntity;
import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.entity.map.Cell;
import lombok.EqualsAndHashCode;

import java.util.LinkedList;
import java.util.Set;

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
    public void eat(Cell currentCell, SettingsEntity settings) {
        Set<Class<?>> setAnimal = currentCell.getSetKind();
        double initialEatenMass = this.eatenMass;
        eatInCell(currentCell, settings, setAnimal);
        checkHungry(initialEatenMass, currentCell);
        super.checkLifeCycle(currentCell, settings);
    }

    @Override
    protected void eatingOrganism(Cell currentCell, LinkedList<Organism> organismList, String organismName) {
        currentCell.deleteOrganismFromStatistics(organismName);
        currentCell.recordDeath(organismName);
        this.eatenMass = 1;
        hungry = false;
        counterHunger = 0;
    }

    @Override
    protected void checkHungry(double currentEatenMass, Cell currentCell) {
        this.eatenMass -= 1;
        if (eatenMass < 0) {
            eatenMass = 0;
            counterHunger++;
            if (counterHunger >= MAX_COUNTER_HUNGER) {
                currentCell.killOrganism(this);
            }
            hungry = true;
        }
    }

    @Override
    public String toString() {
        return "Caterpillar";
    }
}
