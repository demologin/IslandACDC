package com.javarush.island.zhukov.cellWorkers;

import com.javarush.island.zhukov.cellTasking.AnimalsMoving;
import com.javarush.island.zhukov.constans.Constants;

public class WorkerAllCellMoving extends Thread{

    @Override
    public void run() {
        AnimalsMoving animalsMoving = new AnimalsMoving();
        for (int i = 0; i < Constants.WIDTH_GAME; i++) {
            for (int j = 0; j < Constants.HEIGHT_GAME; j++) {
                animalsMoving.move(i,j);
            }
        }
    }
}
