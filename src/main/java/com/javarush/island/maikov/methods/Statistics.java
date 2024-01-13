package com.javarush.island.maikov.methods;

import com.javarush.island.maikov.Abstraction.Organism;
import com.javarush.island.maikov.Animals.Herbivore.Rabbit;
import com.javarush.island.maikov.Animals.Predators.Wolf;
import com.javarush.island.maikov.Grass.Clover;

public class Statistics {
    public static volatile int countWolf = 0;
    public static volatile int countRabbit = 0;
    public static volatile int countClover = 0;


    public void removeFromStatistics(Organism someOrganism) {
        if (someOrganism instanceof Rabbit) {
            countRabbit--;
        }
        if (someOrganism instanceof Wolf) {
            countWolf--;
        }
        if (someOrganism instanceof Clover) {
            countClover--;
        }
    }

    public void addToStatistics(Organism someOrganism) {
        if (someOrganism instanceof Rabbit) {
            countRabbit++;
        }
        if (someOrganism instanceof Wolf) {
            countWolf++;
        }
        if (someOrganism instanceof Clover) {
            countClover++;
        }
    }
}
