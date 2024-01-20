package com.javarush.island.maikov.Abstraction;

import com.javarush.island.maikov.Actions.Eat;
import com.javarush.island.maikov.Actions.Move;
import com.javarush.island.maikov.Actions.Reproduce;
import com.javarush.island.maikov.Constants.Constants;
import com.javarush.island.maikov.MapOfIsland.MapOfIsland;
import com.javarush.island.maikov.methods.Statistics;

import java.util.concurrent.ThreadLocalRandom;


public abstract class AnimalsWorker extends Organism {
    private final Eat eat = new Eat();
    private final Reproduce reproduce = new Reproduce();
    private final Move move = new Move();
    private final Statistics statistics = new Statistics();

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (MapOfIsland.mapOfIsland) {
                checkLife(this);
                if (isHungry(this)) { // is Hungry? Look README
                    int randomAction = ThreadLocalRandom.current().nextInt(0, 2);
                    switch (randomAction) {
                        case 0 -> eat.startEat(this);
                        case 1 -> move.startMove(this);
                    }

                }
                int randomAction = ThreadLocalRandom.current().nextInt(0, 3);
                switch (randomAction) {
                    case 0 -> eat.startEat(this);
                    case 1 -> {
                        try {
                            reproduce.startReproduce(this);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    case 2 -> move.startMove(this);
                }
            }
        }
    }

    private void checkLife(Organism someOrganism) {
        if (someOrganism instanceof Herbivore) {
            if (((Herbivore) someOrganism).getLife() <= 0) {
                ((Herbivore) someOrganism).getThread().interrupt();
                statistics.countDeath();
            }
        }
        if (someOrganism instanceof Predator) {
            if (((Predator) someOrganism).getLife() <= 0) {
                ((Predator) someOrganism).getThread().interrupt();
                statistics.countDeath();
            }
        }
    }


    private boolean isHungry(Organism someOrganism) { // look README.
        if (someOrganism instanceof Herbivore) {
            return ((Herbivore) someOrganism).getLife() < ((Herbivore) someOrganism).getMaxFood() *
                    Constants.minLifeForReproduce;
        }
        if (someOrganism instanceof Predator) {
            return ((Predator) someOrganism).getLife() < ((Predator) someOrganism).getMaxFood() *
                    Constants.minLifeForReproduce;
        }
        return false;
    }
}
