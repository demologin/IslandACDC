package com.javarush.island.zhukov.cellTasking;

import com.javarush.island.zhukov.gameWorld.SetOrganism;
import com.javarush.island.zhukov.organizm.Organism;
import com.javarush.island.zhukov.organizm.Plants;
import com.javarush.island.zhukov.organizm.herbivores.*;
import com.javarush.island.zhukov.organizm.predator.*;
import com.javarush.island.zhukov.statistics.CounterOrganism;
import com.javarush.island.zhukov.utilities.Rnd;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class ReproductionOrganism {
    public void reproduce(int x, int y) {
        synchronized (SetOrganism.animalsInCells[x][y]) {
            Set<Organism> addedAnimals = new HashSet<>();
            for (Organism organism : SetOrganism.animalsInCells[x][y]) {
                if (!Rnd.randomBoolean()) {
                    continue;
                }
                int id = organism.getID();
                AtomicInteger countOrganismThisId = new AtomicInteger();
                countingSuchAnimals(x, y, id, countOrganismThisId, addedAnimals);
                if (countOrganismThisId.get() < organism.getMaxCountInCell()) {
                    addingAnimals(addedAnimals, id, x, y);
                }
            }
            SetOrganism.animalsInCells[x][y].addAll(addedAnimals);
        }
    }

    private void countingSuchAnimals(int x, int y, int id, AtomicInteger countOrganismThisId, Set<Organism> addedAnimals) {
        SetOrganism.animalsInCells[x][y].forEach(e -> {
            if (e.getID() == id) {
                countOrganismThisId.getAndIncrement();
            }
        });
        addedAnimals.forEach(e -> {
            if (e.getID() == id) {
                countOrganismThisId.getAndIncrement();
            }
        });
    }

    private void addingAnimals(Set<Organism> addedAnimals, int id, int x, int y) {
        switch (id) {
            case 0 -> {
                addedAnimals.add(new Wolf(x, y));
                CounterOrganism.countWolf.getAndIncrement();
            }
            case 1 -> {
                addedAnimals.add(new Snake(x, y));
                CounterOrganism.countSnake.getAndIncrement();
            }
            case 2 -> {
                addedAnimals.add(new Fox(x, y));
                CounterOrganism.countFox.getAndIncrement();
            }
            case 3 -> {
                addedAnimals.add(new Bear(x, y));
                CounterOrganism.countBear.getAndIncrement();
            }
            case 4 -> {
                addedAnimals.add(new Eagle(x, y));
                CounterOrganism.countEagle.getAndIncrement();
            }
            case 5 -> {
                addedAnimals.add(new Horse(x, y));
                CounterOrganism.countHorse.getAndIncrement();
            }
            case 6 -> {
                addedAnimals.add(new Deer(x, y));
                CounterOrganism.countDeer.getAndIncrement();
            }
            case 7 -> {
                addedAnimals.add(new Rabbit(x, y));
                CounterOrganism.countRabbit.getAndIncrement();
            }
            case 8 -> {
                addedAnimals.add(new Mouse(x, y));
                CounterOrganism.countMouse.getAndIncrement();
            }
            case 9 -> {
                addedAnimals.add(new Goat(x, y));
                CounterOrganism.countGoat.getAndIncrement();
            }
            case 10 -> {
                addedAnimals.add(new Sheep(x, y));
                CounterOrganism.countSheep.getAndIncrement();
            }
            case 11 -> {
                addedAnimals.add(new Hog(x, y));
                CounterOrganism.countHog.getAndIncrement();
            }
            case 12 -> {
                addedAnimals.add(new Bull(x, y));
                CounterOrganism.countBull.getAndIncrement();
            }
            case 13 -> {
                addedAnimals.add(new Duck(x, y));
                CounterOrganism.countDuck.getAndIncrement();
            }
            case 14 -> {
                addedAnimals.add(new Caterpillar(x, y));
                CounterOrganism.countCaterpillar.getAndIncrement();
            }
            case 15 -> {
                addedAnimals.add(new Plants(x, y));
                CounterOrganism.countPlants.getAndIncrement();
            }
        }
    }
}