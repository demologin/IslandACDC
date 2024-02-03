package com.javarush.island.kgurov.simulation;

import com.javarush.island.kgurov.field.Field;
import com.javarush.island.kgurov.field.GameMap;
import com.javarush.island.kgurov.lifeform.LifeForm;
import com.javarush.island.kgurov.lifeform.animal.Animal;
import com.javarush.island.kgurov.lifeform.animal.herbivore.Caterpillar;
import com.javarush.island.kgurov.lifeform.plant.Plant;
import com.javarush.island.kgurov.main.StatisticsTask;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AnimalEat implements Runnable {
    private final CountDownLatch latch;
    @Getter
    private int animalsEaten;

    public AnimalEat(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        animalsEaten = 0;
        List<Animal> animals = new ArrayList<>(GameMap.getInstance().getAllAnimals());
        if (!animals.isEmpty()) {
            for (Animal currentAnimal : new ArrayList<>(animals)) {
                if (currentAnimal.getMaxHp() > 0) {
                    Field location = GameMap
                            .getInstance()
                            .getLocation(currentAnimal.getRow(), currentAnimal.getCol());
                    List<LifeForm> locationLifeForms = new ArrayList<>(location.getLifeForms());

                    if (!locationLifeForms.isEmpty()) {
                        for (LifeForm lifeForm : locationLifeForms) {
                            if (currentAnimal
                                    .getChanceToEat(lifeForm.getName()) > 0 && !(lifeForm instanceof Caterpillar)) {
                                boolean isEaten = currentAnimal.eat(lifeForm);

                                if (isEaten) {
                                    if (lifeForm instanceof Animal animalEaten) {
                                        GameMap.getInstance()
                                                .removeAnimal(animalEaten, location.getRow(), location.getCol());
                                        animalsEaten++;
                                    } else {
                                        GameMap.getInstance()
                                                .removePlant((Plant) lifeForm, location.getRow(), location.getCol());
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } else if (GameMap.getInstance().getAllAnimals().isEmpty()) {
            System.out.printf("You lose on day %d%n", StatisticsTask.getCurrentDay());
            shutdownAndExit();
        } else {
            System.out.printf("You win on day %d%n", StatisticsTask.getCurrentDay());
            shutdownAndExit();
        }
        latch.countDown();
    }

    private void shutdownAndExit() {
        IslandSimulation.getInstance().getExecutorService().shutdown();
        System.exit(0);
    }
}