package com.javarush.island.kotovych.organisms.animals;

import com.javarush.island.kotovych.exceptions.AppException;
import com.javarush.island.kotovych.factory.OrganismFactory;
import com.javarush.island.kotovych.organisms.Flock;
import com.javarush.island.kotovych.organisms.Organism;
import com.javarush.island.kotovych.game.Square;
import com.javarush.island.kotovych.settings.Settings;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class Animal extends Organism {
    public void reproduce(Square currentSquare, Flock flock, List<Animal> reproducedAnimals) {
        try {
            blockOtherThreadsIntOrganism();
            for (Organism organism : flock.getOrganisms()) {
                Animal animal = (Animal) organism;
                if (!reproducedAnimals.contains(this) && !reproducedAnimals.contains(animal)) {
                    if (currentSquare.getTotalAnimalsInSquare().get() < Settings.getMaxAnimalsOnSquare()
                            && flock.getOrganisms().size() < Settings.getMaxFlockSize()) {
                        flock.addOrganism(OrganismFactory.newOrganism(flock.getName()), currentSquare);
                    }
                }
            }
        } catch (Exception e){
            throw new AppException(e);
        } finally {
            unblockOtherThreadsInOrganism();
        }
    }
}