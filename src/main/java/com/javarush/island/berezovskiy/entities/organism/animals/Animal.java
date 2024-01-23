package com.javarush.island.berezovskiy.entities.organism.animals;

import com.javarush.island.berezovskiy.constants.Constants;
import com.javarush.island.berezovskiy.entities.organism.OrganismsEnum;
import com.javarush.island.berezovskiy.interfaces.Eatable;
import com.javarush.island.berezovskiy.entities.organism.animals.herbivores.Herbivorous;
import com.javarush.island.berezovskiy.entities.organism.animals.predators.Predator;
import com.javarush.island.berezovskiy.entities.organism.Organism;
import com.javarush.island.berezovskiy.entities.organism.plants.Plant;
import com.javarush.island.berezovskiy.utils.Rnd;

import java.util.Optional;

public abstract class Animal extends Organism implements Eatable {
    protected int maximumStep;

    public int getMaximumStep() {
        return maximumStep;
    }

    @Override
    public void eat(Organism organismForFood) {
        if (chanceBeingEaten() && canEat(organismForFood)) {
            this.setStarved(false);
            this.setNotReadyToGiveBirth(false);
            organismForFood.setAlive(false);
        }
    }

    protected boolean canEat(Organism organismForFood) {
        return (this.isStarved() && organismForFood.isAlive()) &&
                (!this.getName().equals(organismForFood.getName())) &&
                ((this instanceof Herbivorous && organismForFood instanceof Plant) ||
                        (this instanceof Predator && organismForFood instanceof Herbivorous) ||
                        (this instanceof Predator && organismForFood instanceof Predator));
    }

    public Optional<Organism> getOrganismChild(Animal organism) {
        Organism organismChild = null;
        String name = organism.giveNameOfNewOrganism();
        if (!name.equals(Constants.UNNAMED)) {
            organismChild = organismFactory.createOrganism(OrganismsEnum.valueOf(name));
        }
        return Optional.ofNullable(organismChild);
    }

    public boolean chanceBeingEaten() {
        return Rnd.getRandomBoolean();
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

    public boolean chanceToGiveBirth() {
        return Rnd.getRandomBoolean();
    }


}
