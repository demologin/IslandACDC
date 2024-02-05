package com.javarush.island.bogomolov.threads.animalthreads;

import com.javarush.island.bogomolov.creatures.Animal;
import com.javarush.island.bogomolov.creatures.Organism;
import com.javarush.island.bogomolov.creatures.plants.Plant;
import com.javarush.island.bogomolov.storage.Cell;
import com.javarush.island.bogomolov.storage.IslandInitialization;
import com.javarush.island.bogomolov.storage.IslandMap;
import com.javarush.island.bogomolov.threads.IslandStatisticThread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AnimalEatThread implements Runnable {
    private final CountDownLatch countDownLatch;
    private int animalsEaten;

    public AnimalEatThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        animalsEaten = 0;
        List<Animal> animals = IslandMap.getislandMap().allTheAnimals();
        List<Organism> organismsEaten = new ArrayList<>();
        int animalsCount = animals.size();
        if (animalsCount > 0 && !animals.stream().filter(animal -> !(animal.getName().equals("Caterpillar"))).toList().isEmpty()) {
            Iterator<Animal> iterator = animals.iterator();
            while (iterator.hasNext()) {
                Animal currentAnimal = iterator.next();
                if (currentAnimal.getRequiredFood() > 0) {
                    Cell cell = IslandMap.getislandMap().getCell(currentAnimal.getRow(), currentAnimal.getColumn());
                    List<Organism> cellOrganisms = cell.getOrganisms();
                    if (!cellOrganisms.isEmpty()) {
                        for (Organism cellOrganism : cellOrganisms) {
                            if (currentAnimal.getChancesToEat(cellOrganism.getName()) > 0 && !(organismsEaten.contains(cellOrganism))) {
                                boolean isEaten = currentAnimal.eat(cellOrganism);
                                if (isEaten) {
                                    if (cellOrganism instanceof Animal animalEaten) {
                                        if (cell.getAnimals().contains(animalEaten)) {
                                            IslandMap.getislandMap().removeAnimal(animalEaten, cell.getRow(), cell.getColumn());
                                        }
                                        organismsEaten.add(animalEaten);
                                        animalsEaten++;
                                    } else {
                                        Plant plant = (Plant) cellOrganism;
                                        if (!cell.getPlants().isEmpty()) {
                                            IslandMap.getislandMap().removePlant(plant, cell.getRow(), cell.getColumn());
                                        }
                                    }
                                }
                                break;
                            }
                        }


                    }
                }
                iterator.remove();
            }
        } else if (animalsCount == 0) {
            System.out.println("Game over");
            IslandInitialization.getIslandInitialization().getExecutorService().shutdown();
            System.exit(0);
        } else {
            System.out.println("Caterpillars win on day:" + IslandStatisticThread.getCurrentDay());
            IslandInitialization.getIslandInitialization().getExecutorService().shutdown();
            System.exit(0);
        }
        countDownLatch.countDown();
    }


    public int getAnimalsEaten() {
        return animalsEaten;
    }
}



