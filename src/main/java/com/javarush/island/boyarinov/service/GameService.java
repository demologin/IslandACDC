package com.javarush.island.boyarinov.service;

import com.javarush.island.boyarinov.constants.Constants;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameService extends Thread {

    private final List<Runnable> services;
    private static final int CORE_PULL_SIZE = Constants.CORE_POOL_SIZE;
    public static final int PERIOD = Constants.PERIOD;

    public GameService(List<Runnable> services) {
        this.services = services;
    }

    @Override
    public void run() {
        ScheduledExecutorService mainService = Executors.newSingleThreadScheduledExecutor();
        mainService.scheduleWithFixedDelay(this::doOneStep, 0, PERIOD, TimeUnit.MILLISECONDS);


    }

    private void doOneStep() {
        try (ExecutorService executorService = Executors.newFixedThreadPool(CORE_PULL_SIZE)) {
            services.forEach(executorService::execute);
            executorService.shutdown();
        }
    }
}
