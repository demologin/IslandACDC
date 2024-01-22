package com.javarush.island.berezovskiy.Entities.Factory;

import com.javarush.island.berezovskiy.Entities.Organism.Animals.Herbivores.*;
import com.javarush.island.berezovskiy.Entities.Organism.Animals.Predators.*;
import com.javarush.island.berezovskiy.Entities.Organism.Organism;
import com.javarush.island.berezovskiy.Entities.Organism.OrganismsEnum;
import com.javarush.island.berezovskiy.Entities.Organism.Plants.Grass;

public class OrganismFactory {

    public Organism createOrganism(OrganismsEnum organism){
        return switch(organism){
            case WOLF -> new Wolf();
            case BOA -> new Boa();
            case FOX -> new Fox();
            case BEAR -> new Bear();
            case EAGLE -> new Eagle();
            case HORSE -> new Horse();
            case DEER -> new Deer();
            case RABBIT -> new Rabbit();
            case MOUSE -> new Mouse();
            case GOAT -> new Goat();
            case SHEEP -> new Sheep();
            case BOAR -> new Boar();
            case BUFFALO -> new Buffalo();
            case DUCK -> new Duck();
            case CATERPILLAR -> new Caterpillar();
            case GRASS -> new Grass();
        };
    }

    public int getMaximinCountOrganism(OrganismsEnum organismsEnum){
        return switch(organismsEnum){
            case WOLF -> new Wolf().getMaximumCount();
            case BOA -> new Boa().getMaximumCount();
            case FOX -> new Fox().getMaximumCount();
            case BEAR -> new Bear().getMaximumCount();
            case EAGLE -> new Eagle().getMaximumCount();
            case HORSE -> new Horse().getMaximumCount();
            case DEER -> new Deer().getMaximumCount();
            case RABBIT -> new Rabbit().getMaximumCount();
            case MOUSE -> new Mouse().getMaximumCount();
            case GOAT -> new Goat().getMaximumCount();
            case SHEEP -> new Sheep().getMaximumCount();
            case BOAR -> new Boar().getMaximumCount();
            case BUFFALO -> new Buffalo().getMaximumCount();
            case DUCK -> new Duck().getMaximumCount();
            case CATERPILLAR -> new Caterpillar().getMaximumCount();
            case GRASS -> new Grass().getMaximumCount();
        };
    }
}
