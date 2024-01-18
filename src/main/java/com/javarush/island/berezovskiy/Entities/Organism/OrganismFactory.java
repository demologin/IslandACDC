package com.javarush.island.berezovskiy.Entities.Organism;

import com.javarush.island.berezovskiy.Entities.Organism.Animals.Herbivores.Rabbit;
import com.javarush.island.berezovskiy.Entities.Organism.Animals.Predators.Wolf;
import com.javarush.island.berezovskiy.Entities.Organism.Plants.Grass;

public class OrganismFactory {

    public Organism createOrganism(OrganismsEnum organism){
        return switch(organism){
            case WOLF -> new Wolf();
            case RABBIT -> new Rabbit();
            case GRASS -> new Grass();
        };
    }

    public int getMaximinCountOrganism(OrganismsEnum organismsEnum){
        return switch(organismsEnum){
            case WOLF -> new Wolf().getMaximumCount();
            case RABBIT -> new Rabbit().getMaximumCount();
            case GRASS -> new Grass().getMaximumCount();
        };
    }
}
