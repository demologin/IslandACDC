package com.javarush.island.berezovskiy.Entities.Organism;

import com.javarush.island.berezovskiy.Entities.Organism.Animals.Animal;

import java.util.HashMap;
import java.util.Map;

public class TaskAnimal {
    public HashMap<String, OrganismsSet> getOrganismHashMap() {
        return organismHashMap;
    }

    private final HashMap<String, OrganismsSet> organismHashMap;

    public TaskAnimal(HashMap<String, OrganismsSet> organismHashMap) {
        this.organismHashMap = organismHashMap;
    }

    public void liveOrganismInCell(HashMap<String, OrganismsSet> organismHashMap){
        for (Map.Entry<String, OrganismsSet> organismEntry : organismHashMap.entrySet()) {
            OrganismsSet organismsSet = organismEntry.getValue();
            Organism organism = organismsSet.getOrganism();
            ////organism.reproduce();
            if(organism instanceof Animal animal){
                //animal.eat();
                //organismsSet.move();
            }
        }
    }
}
