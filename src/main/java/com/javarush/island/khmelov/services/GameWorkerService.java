package com.javarush.island.khmelov.services;

import com.javarush.island.khmelov.api.view.View;
import com.javarush.island.khmelov.config.Setting;
import com.javarush.island.khmelov.entity.Game;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Getter
public class GameWorkerService extends Thread {
    public static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private final Game game;
    private final List<Runnable> services;
    private final int PERIOD = Setting.get().life.getPeriod();
    private ScheduledExecutorService mainPool;
    private View view;

    public GameWorkerService(Game game, List<Runnable> services) {
        this.game = game;
        this.services = services;
    }

    @Override
    public void run() {
        view = game.getView();
        mainPool = Executors.newSingleThreadScheduledExecutor();
        mainPool.scheduleWithFixedDelay(this::doOneStepGame, 0, PERIOD, TimeUnit.MILLISECONDS);
    }

    private void doOneStepGame() {
        try (ExecutorService servicePool = Executors.newFixedThreadPool(CORE_POOL_SIZE)) {
            services.forEach(servicePool::submit);
            servicePool.shutdown();
            awaitAndRepaint(servicePool);
            if (game.isFinished()) {
                mainPool.shutdown();
            }
        }
    }

    @SneakyThrows
    private void awaitAndRepaint(ExecutorService servicePool) {
        if (servicePool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS)) {
            game.getGameMap().updateStatistics();
            view.show();
        }
    }
}
