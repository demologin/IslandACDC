package com.javarush.island.berezovskiy.Entities.Organism;


import com.javarush.island.berezovskiy.Entities.Organism.Animals.Herbivores.Rabbit;
import com.javarush.island.berezovskiy.Entities.Organism.Animals.Predators.Wolf;

public enum OrganismNames {
    RABBIT(new Rabbit()),
    WOLF(new Wolf());

    private final Organism organism;
    OrganismNames(Organism organism){
        this.organism = organism;
    }
}
