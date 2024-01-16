package com.javarush.island.boyarinov.entities.organism.animals;

import com.javarush.island.boyarinov.entities.map.Cell;
import com.javarush.island.boyarinov.entities.map.Island;
import com.javarush.island.boyarinov.entities.organism.Organisms;
import com.javarush.island.boyarinov.interfaces.Dying;
import com.javarush.island.boyarinov.interfaces.Eating;
import com.javarush.island.boyarinov.interfaces.Movable;
import com.javarush.island.boyarinov.interfaces.Multiplying;

import java.util.Set;

public abstract class Animal extends Organisms implements Movable, Eating, Multiplying, Dying {
    
    private int travelSpeed;
    private int maxKgFood;

    public Animal(Island island) {
        super(island);
    }

    @Override
    public boolean move() {
        Cell location = getLocation();
        Cell[][] map = island.getMap();

        int numberAnimal = countNumberAnimal(location.getOrganismsSet());
        if (numberAnimal > getMaxOfAnimalsToCell()) {
            return false;
        }


        return false;
    }

    private int countNumberAnimal(Set<Organisms> organismsSet) {
        int count = 0;
        for (Organisms organisms : organismsSet) {
            Class<? extends Organisms> aClass = organisms.getClass();
            if (aClass.equals(this.getClass())) {
                count++;
            }
        }
        return count;
    }
}
