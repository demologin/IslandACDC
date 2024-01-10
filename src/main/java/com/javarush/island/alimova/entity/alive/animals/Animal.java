package com.javarush.island.alimova.entity.alive.animals;

import com.javarush.island.alimova.api.entity.Eating;
import com.javarush.island.alimova.api.entity.Moving;
import com.javarush.island.alimova.configure.SettingsEntity;
import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.entity.map.Cell;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Organism implements Eating, Moving {

    protected int maxSpeed;
    protected double maxFoodWeight;
    protected double eatenMass;
    protected boolean satiety;

    public Animal(double weight, int maxAmount,
                  int maxSpeed, double maxFoodWeight) {
        super(weight, maxAmount);
        this.maxSpeed = maxSpeed;
        this.maxFoodWeight = maxFoodWeight;
        this.eatenMass = 0;
        this.satiety = false;
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
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxSpeed, maxFoodWeight, eatenMass, satiety);
    }

    @Override
    public void eat(Cell currentCell, SettingsEntity settings) {
        Set<Class<?>> setAnimal = currentCell.getSetKind();
        //эту дорожку надо как-то убрать
        if (!this.satiety) {
            for (Class<?> classOrganism : setAnimal) {
                String organismName = classOrganism.getSimpleName();
                if(willBeEaten(organismName, settings)) {
                    LinkedList<Organism> organismList = (LinkedList<Organism>) currentCell.getListOrganism(classOrganism);
                    //ещё делать что-то при ситуации, что животных нет
                    if (!organismList.isEmpty()) {
                        Organism organism = organismList.removeFirst();
                        this.eatenMass += organism.getWeight();
                        if (this.eatenMass >= this.maxFoodWeight) {
                            this.eatenMass = this.maxFoodWeight;
                            this.satiety = true;
                        }
                        break;
                    }

                }
            }
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

    }
}
