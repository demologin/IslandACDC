package com.javarush.island.khasanov.service;

import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.exception.SimulationException;
import com.javarush.island.khasanov.view.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Getter
public class SimulationWorker {
    private final Island island;
    private final View view;
    private final Statistics statistics;
    public static final int POOL_SIZE = 4;
    public static final int INITIAL_DELAY = 100;
    public static final int DELAY = 1000;
    public static final long TIMEOUT = 1;

    public SimulationWorker(Island island, View view, Statistics statistics) {
        this.island = island;
        this.view = view;
        this.statistics = statistics;
    }

    public void run() {
        island.fill();
        showSimulation();
        List<Runnable> services = new ArrayList<>() {{
            add(new EatingService(island));
            add(new MovingService(island));
            add(new ReproducingService(island));
            add(new DyingService(island));
            add(new GrowingService(island));
        }};
        ScheduledExecutorService mainPool = Executors.newScheduledThreadPool(POOL_SIZE);
        mainPool.scheduleWithFixedDelay(
                () -> startServices(services),
                INITIAL_DELAY,
                DELAY,
                TimeUnit.MILLISECONDS
        );
    }

    private void startServices(List<Runnable> servicesList) {
        ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE);
        servicesList.forEach(executorService::submit);
        executorService.shutdown();
        try {
            if (executorService.awaitTermination(TIMEOUT, TimeUnit.DAYS))
            {
                showSimulation();
            }
        } catch (InterruptedException e) {
            new SimulationException(e).printStackTrace();
        }
    }

    private void showSimulation() {
        statistics.printCompact();
    }

}