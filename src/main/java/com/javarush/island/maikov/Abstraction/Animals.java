package com.javarush.island.maikov.Abstraction;

import com.javarush.island.maikov.Actions.Eat;
import com.javarush.island.maikov.Actions.Move;
import com.javarush.island.maikov.Actions.Reproduce;
import com.javarush.island.maikov.Animals.Herbivore.Rabbit;
import com.javarush.island.maikov.MapOfIsland;

import java.util.concurrent.ThreadLocalRandom;


public abstract class Animals extends Organism {
    private final Reproduce reproduce = new Reproduce();
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (MapOfIsland.mapOfIsland) {
                int randomAction = ThreadLocalRandom.current().nextInt(0, 3);
                if (randomAction == 0) {
                    Eat.startEat(this);
                }
                if (randomAction == 1) {
                    try {
                        reproduce.startReproduce(this);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                if(randomAction == 2){
                    Move.startMove(this);
                }
            }

        }
    }
    private static boolean isHungryHerbivore(Organism anyOrganism) {
        //check is animal hungry. Look README.
        return ((Herbivore) anyOrganism).getMaxFood() * 0.7 < ((Rabbit) anyOrganism).getLive();
    }
}
