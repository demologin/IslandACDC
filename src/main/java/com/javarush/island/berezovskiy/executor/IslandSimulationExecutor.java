package com.javarush.island.berezovskiy.executor;

import com.javarush.island.berezovskiy.configs.Configs;
import com.javarush.island.berezovskiy.constants.Constants;
import com.javarush.island.berezovskiy.entities.cell.Cell;
import com.javarush.island.berezovskiy.entities.Island;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class IslandSimulationExecutor {
    private final Island island;

    public IslandSimulationExecutor(Island island) {
        this.island = island;
    }

    public void startExecutor() {
        startIslandExecutor();
        startCellsExecutor();
    }

    private void startIslandExecutor() {
        ScheduledExecutorService islandExecutor = Executors.newScheduledThreadPool(1);
        islandExecutor.scheduleAtFixedRate(island::run, 1, 5, TimeUnit.SECONDS);
        try {
            if (!islandExecutor.awaitTermination(Configs.TIME_FOR_WAITING, TimeUnit.SECONDS)) {
                islandExecutor.shutdown();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(Constants.ISLAND_EXECUTOR_INVALID_INFORMATION);
        }
    }

    private void startCellsExecutor() {
        int cellsThreadPool = Configs.ISLAND_HEIGHT * Configs.ISLAND_WIDTH;
        ScheduledExecutorService cellExecutor = Executors.newScheduledThreadPool(cellsThreadPool);
        for (
                Cell[] cells : island.getIslandField()) {
            for (Cell cell : cells) {
                cellExecutor.scheduleAtFixedRate(cell.startWorking(), 0, Configs.TIME_FOR_WAITING, TimeUnit.SECONDS);
            }
        }
        try {
            if (!cellExecutor.awaitTermination(Configs.TIME_FOR_WAITING, TimeUnit.SECONDS)) {
                cellExecutor.shutdown();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(Constants.CELLS_EXECUTOR_INVALID_INFORMATION);
        }
    }

}

