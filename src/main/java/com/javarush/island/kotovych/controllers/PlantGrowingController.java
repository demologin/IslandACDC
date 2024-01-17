package com.javarush.island.kotovych.controllers;

import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.organisms.Flock;
import com.javarush.island.kotovych.settings.Settings;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class PlantGrowingController implements Controller {

    private final GameScene gameScene;

    public PlantGrowingController(GameScene gameScene){
        this.gameScene = gameScene;
    }

    @Override
    public void run() {
        Arrays.stream(gameScene.getField())
                .flatMap(Arrays::stream)
                .parallel()
                .forEach(square -> {
                    int number = ThreadLocalRandom.current().nextInt(2);
                    if(number == 1){
                        Flock flock = new Flock("Plant", 10);
                        if(square.getTotalAnimalsInSquare().get() < Settings.getMaxAnimalsOnSquare()){
                            square.addFlock(flock);
                        }
                    }
                });
    }
}
