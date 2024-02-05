package com.javarush.island.kgurov.simulation;

import com.javarush.island.kgurov.field.Field;
import com.javarush.island.kgurov.field.GameMap;
import com.javarush.island.kgurov.lifeform.animal.Animal;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AnimalMultiply implements Runnable {
    @Getter
    private int cubsCount;
    private final CountDownLatch latch;

    public AnimalMultiply(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        cubsCount = 0;
        List<Animal> animals = GameMap.getInstance().getAllAnimals();
        List<Animal> animalsMultiplied = new ArrayList<>();
        if (!animals.isEmpty()) {
            for (Animal currentAnimal : animals) {
                if (!animalsMultiplied.contains(currentAnimal)) {
                    Field location = GameMap.getInstance().getLocation(currentAnimal.getRow(), currentAnimal.getCol());
                    List<Animal> locationAnimals = location.getAnimals();
                    if (locationAnimals.size() > 1) {
                        locationAnimals = locationAnimals.stream().filter(c -> c.getName().equals(currentAnimal.getName()) && c != currentAnimal).toList();
                        if (!locationAnimals.isEmpty()) {
                            Animal partner = locationAnimals.get(0);
                            if (!animalsMultiplied.contains(partner)) {
                                currentAnimal.multiply(partner);
                                animalsMultiplied.add(currentAnimal);
                                animalsMultiplied.add(partner);
                                cubsCount++;
                            }
                        }
                    }
                }
            }
        }
        latch.countDown();
    }
}
