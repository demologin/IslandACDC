package com.javarush.island.maikov.Abstraction;

import com.javarush.island.maikov.Actions.Eat;
import com.javarush.island.maikov.Actions.Reproduce;
import com.javarush.island.maikov.Animals.Herbivore.Herbivore;
import com.javarush.island.maikov.Animals.Herbivore.Rabbit;
import com.javarush.island.maikov.MapOfIsland;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;


public abstract class Animals extends Organism implements Runnable {

    public void move() {
        System.out.println("move");
    }

    @Override

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                synchronized (MapOfIsland.mapOfIsland) {
                    int randomAction = ThreadLocalRandom.current().nextInt(1, 3);
                    switch (randomAction) {
                        case 1 -> Eat.eat(this);
                        case 2 -> Reproduce.reproduce(this);
                    }
                }
                } catch(InterruptedException e){
                    throw new RuntimeException(e);
                }
            }
        }


    private static boolean isHungryHerbivore(Organism anyOrganism) {
        //check is animal hungry. Look README.
        return ((Herbivore) anyOrganism).getMaxFood() * 0.7 < ((Rabbit) anyOrganism).getLive();
    }
}
