package com.javarush.island.maikov.Abstraction;

import com.javarush.island.maikov.Actions.Eat;
import com.javarush.island.maikov.Actions.Move;
import com.javarush.island.maikov.Actions.Reproduce;
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
            }
        }
        if (someOrganism instanceof Predator) {
            if (((Predator) someOrganism).getLife() <= 0) {
                ((Predator) someOrganism).getThread().interrupt();
            }
        }
    }


    private boolean isHungry(Organism someOrganism) { // look README.
        if (someOrganism instanceof Herbivore) {
            return ((Herbivore) someOrganism).getLife() < ((Herbivore) someOrganism).getMaxFood() * 0.7;
        }
        if (someOrganism instanceof Predator) {
            return ((Predator) someOrganism).getLife() < ((Predator) someOrganism).getMaxFood() * 0.7;
        }
        return false;
    }
}
