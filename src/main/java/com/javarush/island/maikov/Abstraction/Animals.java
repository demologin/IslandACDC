package com.javarush.island.maikov.Abstraction;

import com.javarush.island.maikov.Actions.Eat;
import com.javarush.island.maikov.Actions.Move;
import com.javarush.island.maikov.Actions.Reproduce;
import com.javarush.island.maikov.Animals.Herbivore.Herbivore;
import com.javarush.island.maikov.Animals.Herbivore.Rabbit;
import com.javarush.island.maikov.MapOfIsland;

import java.util.concurrent.ThreadLocalRandom;


public abstract class Animals extends Organism {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (MapOfIsland.mapOfIsland) {
                int randomAction = ThreadLocalRandom.current().nextInt(0, 3);
                if (randomAction == 0) {
                    Eat.eat(this);
                }
                if (randomAction == 1) {
                    try {
                        Reproduce.reproduce(this);
                    } catch (InterruptedException e) {
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                        try {
                            Reproduce.reproduce(this); // I decided to run this method again, after it
                                                                    // get an exception, but I don't know if this is
                                                                    // the right solution.
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                if(randomAction == 2){
                    Move.move(this);
                }
            }

        }
    }


    private static boolean isHungryHerbivore(Organism anyOrganism) {
        //check is animal hungry. Look README.
        return ((Herbivore) anyOrganism).getMaxFood() * 0.7 < ((Rabbit) anyOrganism).getLive();
    }
}
