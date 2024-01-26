package com.javarush.island.bogomolov.threads.animalthreads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnimalThreads implements Runnable {
    private final CountDownLatch countDownLatch;

    private final AnimalEatThread animalEatThread;

    private final AnimalDieOfHungerThread animalDieOfHungerThread;
    private final AnimalsSpawnThread animalsSpawnThread;

    public AnimalThreads() {
        countDownLatch = new CountDownLatch(3);
        animalEatThread = new AnimalEatThread(countDownLatch);
        animalDieOfHungerThread = new AnimalDieOfHungerThread(countDownLatch);
        animalsSpawnThread = new AnimalsSpawnThread(countDownLatch);
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

            executorService.submit(animalEatThread);
            executorService.submit(animalDieOfHungerThread);
            executorService.submit(animalsSpawnThread);
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            executorService.submit(new AnimalMoveThread());


    }

    public AnimalEatThread getAnimalEatThread() {
        return animalEatThread;
    }

    public AnimalDieOfHungerThread getAnimalDieOfHungerThread() {
        return animalDieOfHungerThread;
    }

    public AnimalsSpawnThread getAnimalsSpawnThread() {
        return animalsSpawnThread;
    }
}
