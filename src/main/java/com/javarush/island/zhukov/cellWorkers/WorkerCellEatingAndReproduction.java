package com.javarush.island.zhukov.cellWorkers;

import com.javarush.island.zhukov.cellTasking.AnimalsEating;
import com.javarush.island.zhukov.cellTasking.ReproductionOrganism;

public class WorkerCellEatingAndReproduction extends Thread {
    private final int x;
    private final int y;

    public WorkerCellEatingAndReproduction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {
        AnimalsEating animalsEating = new AnimalsEating();
        ReproductionOrganism reproductionOrganism = new ReproductionOrganism();
        animalsEating.eat(x, y);
        reproductionOrganism.reproduce(x, y);
    }
}
