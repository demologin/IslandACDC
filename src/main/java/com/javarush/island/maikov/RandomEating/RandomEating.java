package com.javarush.island.maikov.RandomEating;


import com.javarush.island.maikov.Abstraction.Organism;
import com.javarush.island.maikov.Constants.Constants;
import com.javarush.island.maikov.Organism.Grass.Clover;
import com.javarush.island.maikov.Organism.Herbivore.*;
import com.javarush.island.maikov.Organism.Predators.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


public class RandomEating {
    private static final Map<Class<? extends Organism>, Integer> mapOfFood = new HashMap<>();
    static { // I've done so, because Map.of only supports 10 values!
                mapOfFood.put(Wolf.class, 1);
                mapOfFood.put(Boa.class, 2);
                mapOfFood.put(Fox.class, 3);
                mapOfFood.put(Bear.class, 4);
                mapOfFood.put(Eagle.class, 5);
                mapOfFood.put(Horse.class, 6);
                mapOfFood.put(Deer.class, 7);
                mapOfFood.put(Rabbit.class, 8);
                mapOfFood.put(Mouse.class, 9);
                mapOfFood.put(Goat.class, 10);
                mapOfFood.put(Sheep.class, 11);
                mapOfFood.put(Boar.class, 12);
                mapOfFood.put(Buffalo.class, 13);
                mapOfFood.put(Duck.class, 14);
                mapOfFood.put(Caterpillar.class, 15);
                mapOfFood.put(Clover.class, 16);
    }
    public boolean getRandomEating(Organism organism, Organism eatenOrganism){
        Class<? extends Organism> classOrganism = organism.getClass();
        Class<? extends Organism> classEatenOrganism = eatenOrganism.getClass();
        int mapOfFoodX = mapOfFood.get(classOrganism);
        int mapOfFoodY = mapOfFood.get(classEatenOrganism);
        int opportunityValue = Constants.setProbablyTable[mapOfFoodX][mapOfFoodY];
        return ThreadLocalRandom.current().nextInt(0, 100) < opportunityValue;
    }
}