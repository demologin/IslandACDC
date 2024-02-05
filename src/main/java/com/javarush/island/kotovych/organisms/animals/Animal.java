package com.javarush.island.kotovych.organisms.animals;

import com.javarush.island.kotovych.exceptions.AppException;
import com.javarush.island.kotovych.factory.OrganismFactory;
import com.javarush.island.kotovych.organisms.Flock;
import com.javarush.island.kotovych.organisms.Organism;
import com.javarush.island.kotovych.game.Square;
import com.javarush.island.kotovych.settings.Settings;
import com.javarush.island.kotovych.util.Rnd;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class Animal extends Organism {
    public void reproduce(Square currentSquare, Flock flock, List<Animal> reproducedAnimals) {
        try {
            blockOtherThreadsInOrganism();
            for (Organism organism : flock.getOrganisms()) {
                Animal animal = (Animal) organism;
                int children = Rnd.nextInt(1, 4);
                if (!reproducedAnimals.contains(this) && !reproducedAnimals.contains(animal)) {
                    if (currentSquare.getTotalAnimalsInSquare().get() + children <= Settings.getMaxAnimalsOnSquare()
                            && flock.getOrganisms().size() < Settings.getMaxFlockSize()
                            && flock.getMaxOnOneSquare() < currentSquare.getOrganismCount().get(flock.getName()).get()) {
                        for(int i = 0; i < children; i++) {
                            flock.addOrganism(OrganismFactory.newOrganism(flock.getName()), currentSquare);
                        }
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