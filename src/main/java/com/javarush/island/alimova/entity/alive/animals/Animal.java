package com.javarush.island.alimova.entity.alive.animals;

import com.javarush.island.alimova.api.entity.Eating;
import com.javarush.island.alimova.api.entity.Moving;
import com.javarush.island.alimova.configure.DefaultSettings;
import com.javarush.island.alimova.configure.SettingsEntity;
import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.entity.map.Cell;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Organism implements Eating, Moving {

    protected int maxSpeed;
    protected double maxFoodWeight;
    protected double eatenMass;

    @Getter
    @Setter
    protected boolean satiety;

    protected boolean hungry;
    protected int counterHunger;
    protected final int MAX_COUNTER_HUNGER = 10;

    protected int lifeCycle = 0;

    public Animal(double weight, int maxAmount,
                  int maxSpeed, double maxFoodWeight, boolean satiety) {
        super(weight, maxAmount);
        this.maxSpeed = maxSpeed;
        this.maxFoodWeight = maxFoodWeight;
        this.eatenMass = 0;
        this.satiety = satiety;
        this.hungry = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Animal animal = (Animal) o;
        return maxSpeed == animal.maxSpeed && Double.compare(maxFoodWeight, animal.maxFoodWeight) == 0 && Double.compare(eatenMass, animal.eatenMass) == 0 && satiety == animal.satiety;
    }

    @Override
    public void multiply(Cell currentCell) {
        long amountGrass = currentCell.checkAmountOrganism(this.getClass().getSimpleName());
        if (amountGrass < this.maxAmount) {
            try {
                this.eatenMass = 0;
                this.satiety = false;
                Organism newOrganism = this.clone();
                currentCell.addOrganismToQueueWithStatistic(newOrganism);
            } catch (CloneNotSupportedException e) {
                System.err.println(DefaultSettings.MESSAGE_FATAL_ERROR);
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxSpeed, maxFoodWeight, eatenMass, satiety);
    }

    @Override
    public void eat(Cell currentCell, SettingsEntity settings) {
        Set<Class<?>> setAnimal = currentCell.getSetKind();
        double initialEatenMass = this.eatenMass;
        if (!this.satiety) {
            eatInCell(currentCell, settings, setAnimal);
        }
        checkHungry(initialEatenMass, currentCell);
        checkLifeCycle(currentCell, settings);
    }

    protected void checkHungry(double currentEatenMass, Cell currentCell) {
        if (currentEatenMass == eatenMass) {
            this.eatenMass -= maxFoodWeight * 0.1;
        }
        if (eatenMass < 0) {
            eatenMass = 0;
            counterHunger++;
            if (counterHunger >= MAX_COUNTER_HUNGER) {
                currentCell.killOrganism(this);
            }
            hungry = true;
        }
    }

    protected void checkLifeCycle(Cell currentCell, SettingsEntity settings) {
        lifeCycle++;
        if (lifeCycle >= settings.maxLifeCycle && checkAlive()) {
            currentCell.killOrganism(this);
        }
    }

    protected void eatInCell(Cell currentCell, SettingsEntity settings, Set<Class<?>> setAnimal) {
        for (Class<?> classOrganism : setAnimal) {
            String organismName = classOrganism.getSimpleName();
            if(willBeEaten(organismName, settings)) {
                LinkedList<Organism> organismList = (LinkedList<Organism>) currentCell.getListOrganism(classOrganism);
                if (!organismList.isEmpty()) {
                    eatingOrganism(currentCell, organismList, organismName);
                    break;
                }

            }
        }
    }

    protected void eatingOrganism(Cell currentCell, LinkedList<Organism> organismList, String organismName) {
        Organism organism;
        organism = organismList.removeFirst();
        currentCell.deleteOrganismFromStatistics(organismName);
        currentCell.recordDeath(organismName);
        this.eatenMass += organism.getWeight();
        hungry = false;
        counterHunger = 0;
        if (eatenMass >= maxFoodWeight) {
            eatenMass = maxFoodWeight;
            satiety = true;
        }
    }

    public boolean willBeEaten(String targetName, SettingsEntity settings) {
        String ownName = this.getClass().getSimpleName();
        int currentProbability = settings.eatingTable[settings.getIndexOrganism(ownName)][settings.getIndexOrganism(targetName)];
        if (currentProbability == 100) {
            return true;
        }
        int probabilityEating = ThreadLocalRandom.current().nextInt(0, 100);
        return probabilityEating >= (100 - currentProbability);

    }

    @Override
    public void move(Cell terminalCell) {
        if (this.hungry && checkAlive()) {
            terminalCell.addAnimalToMove(this);
        }
    }

    public boolean checkAlive() {
        return counterHunger < MAX_COUNTER_HUNGER;
    }
}
