package com.javarush.island.zhukov.runners;

import com.javarush.island.zhukov.cellWorkers.WorkerCellEatingAndReproduction;
import com.javarush.island.zhukov.cellWorkers.WorkerAllCellMoving;
import com.javarush.island.zhukov.constans.Constants;
import com.javarush.island.zhukov.constans.TextConstants;
import com.javarush.island.zhukov.gameWorld.CreateWorld;
import com.javarush.island.zhukov.statistics.CounterOrganism;

import java.util.ArrayList;
import java.util.List;

public class ConsoleRunner {
    public void starting(){
        creatingWorld();
        System.out.println(TextConstants.createWorldFinish);
        int x = Constants.NUMBER_IMPRESSIONS_BEFORE_DESTRUCTION;
        while(x>0) {
            CounterOrganism counterOrganism = new CounterOrganism();
            WorkerAllCellMoving runnerCellMoving = new WorkerAllCellMoving();
            List<WorkerCellEatingAndReproduction> listRunners = new ArrayList<>();
            addingWorkersCellAtList(listRunners);
            for (WorkerCellEatingAndReproduction worker : listRunners) {
                worker.start();
            }
            for (WorkerCellEatingAndReproduction worker : listRunners) {
                joiner(worker);
            }
            runnerCellMoving.start();
            joiner(runnerCellMoving);
            sleepingThread();
            counterOrganism.printOrganism();
            x--;
        }
    }

    private void creatingWorld() {
        CreateWorld createWorld = new CreateWorld();
        createWorld.start();
        joiner(createWorld);
    }

    private static void addingWorkersCellAtList(List<WorkerCellEatingAndReproduction> listRunners) {
        for (int i = 0; i < Constants.WIDTH_GAME; i++) {
            for (int j = 0; j < Constants.HEIGHT_GAME; j++) {
                listRunners.add(new WorkerCellEatingAndReproduction(i, j));
            }
        }
    }

    private void joiner(Thread runnerCell) {
        try {
            runnerCell.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void sleepingThread() {
        try {
            Thread.sleep(Constants.FREQUENCY_STATISTICS_DISPLAY_MILLISECOND);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
