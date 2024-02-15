package com.javarush.island.zhukov.cellTasking;

import com.javarush.island.zhukov.constans.Constants;
import com.javarush.island.zhukov.gameWorld.SetOrganism;
import com.javarush.island.zhukov.organizm.Animal;
import com.javarush.island.zhukov.organizm.Organism;
import com.javarush.island.zhukov.organizm.Plants;
import com.javarush.island.zhukov.statistics.CounterOrganism;
import com.javarush.island.zhukov.utilities.Rnd;

import java.util.Iterator;

public class AnimalsEating {
    public void eat(int x, int y) {
        synchronized (SetOrganism.animalsInCells[x][y]) {
            for (Organism organism : SetOrganism.animalsInCells[x][y]) {
                if (organism instanceof Plants || organism.getWeight() == 0) {
                    continue;
                }
                Animal animal = (Animal) organism;
                double animalWeight = animal.getWeight();
                boolean isEating = Rnd.randomBoolean();
                if (!isEating) {
                    weightLossOrDeath(animalWeight, animal);
                    continue;
                }
                eatingProcess(x, y, animal, animalWeight);
            }
            removeDeadOrganism(x, y);
        }
    }

    private static void eatingProcess(int x, int y, Animal animal, double animalWeight) {
        double countEating = animal.getFullNotHungry();
        int animalID = animal.getID();
        for (Organism victim : SetOrganism.animalsInCells[x][y]) {
            if (isAliveOrganism(victim)) {
                continue;
            }
            boolean probabilityEaten = Rnd.probabilityCalc(Constants.PROBABILITY_EATING[animalID][victim.getID()]);
            if (probabilityEaten) {
                if (countEating <= victim.getWeight()) {
                    animalWeight += countEating;
                } else {
                    animalWeight += victim.getWeight();
                }
                victim.setWeight(0);
                animal.setWeight(animalWeight);
            }
        }
    }

    private static boolean isAliveOrganism(Organism victim) {
        return victim.getWeight() == 0;
    }

    private void removeDeadOrganism(int x, int y) {
        Iterator<Organism> iterator = SetOrganism.animalsInCells[x][y].iterator();
        while (iterator.hasNext()) {
            Organism organism = iterator.next();
            if (isAliveOrganism(organism)) {
                iterator.remove();
                deadOrganism(organism);
            }
        }
    }

    private void weightLossOrDeath(double weight, Animal animal) {
        weight -= animal.getFullNotHungry();
        if (weight <= 0) {
            animal.setWeight(0);
        } else {
            animal.setWeight(weight);
        }
    }

    private void deadOrganism(Organism organism) {
        int id = organism.getID();
        SetOrganism.animalsInCells[organism.getX()][organism.getY()].remove(organism);
        switch (id) {
            case 0 -> CounterOrganism.countWolf.getAndDecrement();
            case 1 -> CounterOrganism.countSnake.getAndDecrement();
            case 2 -> CounterOrganism.countFox.getAndDecrement();
            case 3 -> CounterOrganism.countBear.getAndDecrement();
            case 4 -> CounterOrganism.countEagle.getAndDecrement();
            case 5 -> CounterOrganism.countHorse.getAndDecrement();
            case 6 -> CounterOrganism.countDeer.getAndDecrement();
            case 7 -> CounterOrganism.countRabbit.getAndDecrement();
            case 8 -> CounterOrganism.countMouse.getAndDecrement();
            case 9 -> CounterOrganism.countGoat.getAndDecrement();
            case 10 -> CounterOrganism.countSheep.getAndDecrement();
            case 11 -> CounterOrganism.countHog.getAndDecrement();
            case 12 -> CounterOrganism.countBull.getAndDecrement();
            case 13 -> CounterOrganism.countDuck.getAndDecrement();
            case 14 -> CounterOrganism.countCaterpillar.getAndDecrement();
            case 15 -> CounterOrganism.countPlants.getAndDecrement();

        }
    }
}
