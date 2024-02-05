package com.javarush.island.kgurov.simulation;

import com.javarush.island.kgurov.field.Field;
import com.javarush.island.kgurov.field.GameMap;
import com.javarush.island.kgurov.lifeform.animal.Animal;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AnimalHpDecrease implements Runnable {
    private static double PERCENT_HP_TO_DECREASE = 15;
    private static final int TIME_THRESHOLD = 180;
    private final CountDownLatch latch;
    @Getter
    private int animalsDiedByHungry;
    public AnimalHpDecrease(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        animalsDiedByHungry = 0;
        List<Animal> animals = getHealthyAnimals();

        if (IslandSimulation.getInstance().getTimeNow() >= TIME_THRESHOLD) {
            adjustDecreasePercentage();
        }

        for (Animal animal : animals) {
            decreaseAnimalHp(animal);
        }

        latch.countDown();
    }

    private List<Animal> getHealthyAnimals() {
        return GameMap.getInstance().getAllAnimals().stream()
                .filter(c -> c.getMaxHp() > 0)
                .toList();
    }

    private void adjustDecreasePercentage() {
        PERCENT_HP_TO_DECREASE *= 2;
    }

    private void decreaseAnimalHp(Animal animal) {
        double hpToDecrease = animal.getMaxHp() *   PERCENT_HP_TO_DECREASE / 100.0;
        if (animal.getHp() - hpToDecrease > 0) {
            animal.setHp(animal.getHp() - hpToDecrease);
        } else {
            handleStarvation(animal);
        }
    }

    private void handleStarvation(Animal animal) {
        Field location = GameMap.getInstance().getLocation(animal.getRow(), animal.getCol());
        GameMap.getInstance().removeAnimal(animal, location.getRow(), location.getCol());
        animalsDiedByHungry++;
    }
}
