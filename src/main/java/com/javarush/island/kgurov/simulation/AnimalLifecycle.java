package com.javarush.island.kgurov.simulation;

import lombok.Getter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AnimalLifecycle implements Runnable {
    @Getter
    private final AnimalEat animalEat;
    private final AnimalMultiply animalMultiply;
    @Getter
    private final AnimalHpDecrease animalHpDecrease;
    private final CountDownLatch latch;

    public AnimalLifecycle() {
        latch = new CountDownLatch(3);
        animalEat = new AnimalEat(latch);
        animalMultiply = new AnimalMultiply(latch);
        animalHpDecrease = new AnimalHpDecrease(latch);
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(animalEat);
        executorService.submit(animalMultiply);
        executorService.submit(animalHpDecrease);
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.submit(new AnimalMove());
    }

    public AnimalMultiply getObjectMultiplyTask() {
        return animalMultiply;
    }

}
