package com.javarush.island.berezovskiy.Entities.Organism.Animals;

import com.javarush.island.berezovskiy.Constants.Constants;
import com.javarush.island.berezovskiy.Entities.Eatable;
import com.javarush.island.berezovskiy.Entities.Organism.Animals.Herbivores.Herbivorous;
import com.javarush.island.berezovskiy.Entities.Organism.Animals.Predators.Predator;
import com.javarush.island.berezovskiy.Entities.Organism.Organism;
import com.javarush.island.berezovskiy.Entities.Organism.OrganismsEnum;
import com.javarush.island.berezovskiy.Entities.Organism.Plants.Plant;
import com.javarush.island.berezovskiy.Entities.Organism.Reproducible;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Animal extends Organism implements Eatable {
    protected int maximumStep;
    public int getMaximumStep() {
        return maximumStep;
    }
    @Override
    public void eat(Organism organismForFood) {
        if ((this instanceof Herbivorous && organismForFood instanceof Plant) || (this instanceof Predator && organismForFood instanceof Herbivorous)) {
            if (chanceBeingEaten()) {
                this.starved = false;
                this.notReadyToGiveBirth = false;
                organismForFood.setAlive(false);
            }
        }
    }

    @Override
    public String giveNameOfNewOrganism() {
        String name = Constants.UNNAMED;
        this.starved = true;
        this.notReadyToGiveBirth = true;
        if (chanceToGiveBirth()) {
            name = this.getName();
        }
        return name;

    }

    public boolean chanceBeingEaten() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    public boolean chanceToGiveBirth() {
        return ThreadLocalRandom.current().nextBoolean();
    }


}
