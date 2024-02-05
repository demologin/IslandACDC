package com.javarush.island.kotovych.controllers;

import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.organisms.Flock;
import com.javarush.island.kotovych.settings.Settings;
import com.javarush.island.kotovych.util.Rnd;

import java.util.Arrays;

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
                    int number = Rnd.nextInt(10);
                    if(number == 1){
                        Flock flock = new Flock("Plant", 5);
                        if(square.getTotalAnimalsInSquare().get() < Settings.getMaxAnimalsOnSquare()){
                            square.addFlock(flock);
                        }
                    }
                });
    }
}
