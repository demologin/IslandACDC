package com.javarush.island.berezovskiy;

import com.javarush.island.berezovskiy.Configs.Configs;
import com.javarush.island.berezovskiy.Entities.Cell.Cell;
import com.javarush.island.berezovskiy.Entities.Island;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class IslandSimulation {
    public static void main(String[] args) {
        Island island = new Island();
        int threadPool = Configs.ISLAND_HEIGHT*Configs.ISLAND_WIDTH;
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(island::run, 1, 5, TimeUnit.SECONDS);

        try {
            scheduledExecutorService.awaitTermination(Configs.TIME_FOR_WAITING, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ScheduledExecutorService cellExecutor = Executors.newScheduledThreadPool(threadPool);

        for (Cell[] cells : island.getIsland()) {
            for (Cell cell : cells) {
                cellExecutor.scheduleAtFixedRate(cell.startWorking(),0,Configs.TIME_FOR_WAITING,TimeUnit.SECONDS);
            }
        }
        try {
            cellExecutor.awaitTermination(Configs.TIME_FOR_WAITING, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}

