package com.javarush.island.maikov.Abstraction;

import com.javarush.island.maikov.Actions.Reproduce;
import com.javarush.island.maikov.Animals.Herbivore.Herbivore;
import com.javarush.island.maikov.Animals.Herbivore.Rabbit;

import java.lang.reflect.InvocationTargetException;


public abstract class Animals extends Organism implements Runnable {
    public void eat() {
        System.out.println("eat");
    }

    public void move() {
        System.out.println("move");
    }

    @Override
    public void run() {
        while (!Thread.interrupted()){
            try {
                Reproduce.reproduce(this);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static boolean isHungryHerbivore(Organism anyOrganism) {
        //check is animal hungry. Look README.
        return ((Herbivore) anyOrganism).getMaxFood() * 0.7 < ((Rabbit) anyOrganism).getLive();
    }
}
