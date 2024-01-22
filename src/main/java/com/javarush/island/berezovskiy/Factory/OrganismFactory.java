package com.javarush.island.berezovskiy.Factory;

import com.javarush.island.berezovskiy.Configs.Configs;
import com.javarush.island.berezovskiy.Configs.OrganismConfigs;
import com.javarush.island.berezovskiy.Constants.Constants;
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
            case WOLF -> OrganismConfigs.MAX_WOLF_COUNT_IN_CELL;
            case BOA -> OrganismConfigs.MAX_BOA_COUNT_IN_CELL;
            case FOX -> OrganismConfigs.MAX_FOX_COUNT_IN_CELL;
            case BEAR -> OrganismConfigs.MAX_BEAR_COUNT_IN_CELL;
            case EAGLE -> OrganismConfigs.MAX_EAGLE_COUNT_IN_CELL;
            case HORSE -> OrganismConfigs.MAX_HORSE_COUNT_IN_CELL;
            case DEER -> OrganismConfigs.MAX_DEER_COUNT_IN_CELL;
            case RABBIT -> OrganismConfigs.MAX_RABBIT_COUNT_IN_CELL;
            case MOUSE -> OrganismConfigs.MAX_MOUSE_COUNT_IN_CELL;
            case GOAT -> OrganismConfigs.MAX_GOAT_COUNT_IN_CELL;
            case SHEEP -> OrganismConfigs.MAX_SHEEP_COUNT_IN_CELL;
            case BOAR -> OrganismConfigs.MAX_BOAR_COUNT_IN_CELL;
            case BUFFALO -> OrganismConfigs.MAX_BUFFALO_COUNT_IN_CELL;
            case DUCK -> OrganismConfigs.MAX_DUCK_COUNT_IN_CELL;
            case CATERPILLAR -> OrganismConfigs.MAX_CATERPILLAR_COUNT_IN_CELL;
            case GRASS -> OrganismConfigs.MAX_GRASS_COUNT_IN_CELL;
        };
    }
}
