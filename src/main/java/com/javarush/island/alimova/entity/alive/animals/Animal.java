package com.javarush.island.alimova.entity.alive.animals;

import com.javarush.island.alimova.api.entity.Eating;
import com.javarush.island.alimova.api.entity.Moving;
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

    public Animal(double weight, int maxAmount,
                  int maxSpeed, double maxFoodWeight) {
        super(weight, maxAmount);
        this.maxSpeed = maxSpeed;
        this.maxFoodWeight = maxFoodWeight;
        this.eatenMass = 0;
        this.satiety = false;
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
        long amountGrass = currentCell.checkAmountOrganism(this.getClass().getSimpleName());  //может быть хранить где-то имя
        if (amountGrass < this.maxAmount) {
            try {
                this.eatenMass = 0;
                this.satiety = false;
                Organism newOrganism = this.clone();
                currentCell.addOrganismToQueueWithStatistic(newOrganism);
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        } else {
            //todo //кинуть какое-то исключение? либо возвращать булеан
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

    }

    private void checkHungry(double currentEatenMass, Cell currentCell) {
        if (currentEatenMass == eatenMass) {
            this.eatenMass -= maxFoodWeight * 0.05;
        }
        if (eatenMass < 0) {
            eatenMass = 0;
            counterHunger++;
            if (counterHunger >= MAX_COUNTER_HUNGER) {
                currentCell.killOrganism(this);
            }
            hungry = true;      //надо убирать этот флаг
        }
    }

    private void eatInCell(Cell currentCell, SettingsEntity settings, Set<Class<?>> setAnimal) {
        for (Class<?> classOrganism : setAnimal) {
            String organismName = classOrganism.getSimpleName();
            if(willBeEaten(organismName, settings)) {
                LinkedList<Organism> organismList = (LinkedList<Organism>) currentCell.getListOrganism(classOrganism);
                //ещё делать что-то при ситуации, что животных нет
                if (!organismList.isEmpty()) {
                    eatingOrganism(currentCell, organismList, organismName);
                    break;
                }

            }
        }
    }

    private void eatingOrganism(Cell currentCell, LinkedList<Organism> organismList, String organismName) {
        Organism organism;
        organism = organismList.removeFirst();      //тут наверное нужно синхронизовать
        currentCell.deleteOrganismFromStatistics(organismName);
        //System.out.print(this + " kill " + organism.toString() + "; ");
        this.eatenMass += organism.getWeight();
        hungry = false;
        counterHunger = 0;
        if (eatenMass >= maxFoodWeight) {
            eatenMass = maxFoodWeight;
            satiety = true;
            //System.out.println(this + "SATIETY");
        }
    }

    public boolean willBeEaten(String targetName, SettingsEntity settings) {
        String ownName = this.getClass().getSimpleName();
        int currentProbability = settings.eatingTable[settings.getIndexOrganism(ownName)][settings.getIndexOrganism(targetName)];
        if (currentProbability == 100) {
            return true;
        }
        int probabilityEating = ThreadLocalRandom.current().nextInt(0, 100);    //как-то не так считаю вероятность
        if (probabilityEating >= (100 - currentProbability)) {
            return true;
        } else {
            return false;        }

    }

    @Override
    public void move(Cell terminalCell) {
        if (this.hungry && !checkAlive()) {
            terminalCell.addAnimalToMove(this);
        }
    }

    public boolean checkAlive() {
        return counterHunger >= MAX_COUNTER_HUNGER;
    }
}
