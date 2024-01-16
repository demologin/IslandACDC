package com.javarush.island.maikov.Abstraction;

import com.javarush.island.maikov.Actions.Eat;
import com.javarush.island.maikov.Actions.Move;
import com.javarush.island.maikov.Actions.Reproduce;
import com.javarush.island.maikov.Animals.Herbivore.Rabbit;
import com.javarush.island.maikov.MapOfIsland;

import java.util.concurrent.ThreadLocalRandom;


public abstract class Animals extends Organism {
    private final Eat eat = new Eat();
    private final Reproduce reproduce = new Reproduce();
    private final Move move = new Move();

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (MapOfIsland.mapOfIsland) {
                int randomAction = ThreadLocalRandom.current().nextInt(0, 3);
                if (randomAction == 0) {
                    eat.startEat(this);
                }
                if (randomAction == 1) {
                    try {
                        reproduce.startReproduce(this);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                if (randomAction == 2) {
                    move.startMove(this);
                }
            }
        }
    }

    private boolean isHungryHerbivore(Organism anyOrganism) {
        //check is animal hungry. Look README.
        return ((Herbivore) anyOrganism).getMaxFood() * 0.7 < ((Rabbit) anyOrganism).getLive();
    }
}
