package com.javarush.island.kotovych.controllers;

import com.javarush.island.kotovych.factory.OrganismFactory;
import com.javarush.island.kotovych.scene.GameScene;
import com.javarush.island.kotovych.scene.Square;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class PlantGrowingController implements Controller{
    GameScene gameScene;

    public PlantGrowingController(GameScene gameScene){
        this.gameScene = gameScene;
    }
    @Override
    public void execute() {
        for(int x = 0; x < gameScene.getField().length; x++){
            Square[] col = gameScene.getField()[x];
            Arrays.stream(col)
                    .parallel()
                    .forEach(square -> {
                        int randomNumber = ThreadLocalRandom.current().nextInt(2);
                        if(randomNumber == 1){
                            for(int i = 0; i < 10; i++){
                                square.addOrganism(OrganismFactory.newOrganism("Plant"));
                            }
                        }
                    });
        }
    }

    @Override
    public void run() {
        Thread.currentThread().setName(this.getClass().getSimpleName());
        execute();
    }
}
