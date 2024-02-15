package com.javarush.island.zhukov.gameWorld;

import com.javarush.island.zhukov.constans.Constants;
import com.javarush.island.zhukov.organizm.Organism;
import com.javarush.island.zhukov.organizm.Plants;
import com.javarush.island.zhukov.organizm.herbivores.*;
import com.javarush.island.zhukov.organizm.predator.*;
import com.javarush.island.zhukov.statistics.CounterOrganism;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class CreateWorld extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < Constants.WIDTH_GAME; i++) {
            for (int j = 0; j < Constants.HEIGHT_GAME; j++) {
                SetOrganism.animalsInCells[i][j] = createOrganism(i,j);
            }
        }
    }

    private Set<Organism> createOrganism(int x, int y) {
        HashSet<Organism> animalSetInCell = new HashSet<>();
        int countAnimalsInCell = ThreadLocalRandom.current().nextInt(1,16);
        if (countAnimalsInCell % 3 == 0) {
            for (int i = 0; i < countAnimalsInCell * 10; i++) {
                animalSetInCell.add(new Plants(x, y));
                CounterOrganism.countPlants.getAndIncrement();
            }
        }
        while (countAnimalsInCell > 0) {
            addRandomAnimalInSetCell(animalSetInCell, x, y);
            countAnimalsInCell--;
        }
        return animalSetInCell;
    }

    private void addRandomAnimalInSetCell(Set<Organism> animalSetInCell, int x, int y) {
        int randomType = ThreadLocalRandom.current().nextInt(1, 16);
        Organism organism = getOrganism(x, y, randomType);
        animalSetInCell.add(organism);
    }

    private Organism getOrganism(int x, int y, int randomType) {
        return switch (randomType) {
            case 1 -> {
                CounterOrganism.countBull.getAndIncrement();
                yield new Bull(x, y);
            }
            case 2 -> {
                CounterOrganism.countCaterpillar.getAndIncrement();
                yield new Caterpillar(x, y);
            }
            case 3 -> {
                CounterOrganism.countDeer.getAndIncrement();
                yield new Deer(x, y);
            }
            case 4 -> {
                CounterOrganism.countDuck.getAndIncrement();
                yield new Duck(x, y);
            }
            case 5 -> {
                CounterOrganism.countGoat.getAndIncrement();
                yield new Goat(x, y);
            }
            case 6 -> {
                CounterOrganism.countHog.getAndIncrement();
                yield new Hog(x, y);
            }
            case 7 -> {
                CounterOrganism.countHorse.getAndIncrement();
                yield new Horse(x, y);
            }
            case 8 -> {
                CounterOrganism.countMouse.getAndIncrement();
                yield new Mouse(x, y);
            }
            case 9 -> {
                CounterOrganism.countRabbit.getAndIncrement();
                yield new Rabbit(x, y);
            }
            case 10 -> {
                CounterOrganism.countSheep.getAndIncrement();
                yield new Sheep(x, y);
            }
            case 11 -> {
                CounterOrganism.countBear.getAndIncrement();
                yield new Bear(x, y);
            }
            case 12 -> {
                CounterOrganism.countEagle.getAndIncrement();
                yield new Eagle(x, y);
            }
            case 13 -> {
                CounterOrganism.countFox.getAndIncrement();
                yield new Fox(x, y);
            }
            case 14 -> {
                CounterOrganism.countSnake.getAndIncrement();
                yield new Snake(x, y);
            }
            case 15 -> {
                CounterOrganism.countWolf.getAndIncrement();
                yield new Wolf(x, y);
            }
            default -> throw new IllegalStateException("Unexpected value: " + randomType);
        };
    }
}
