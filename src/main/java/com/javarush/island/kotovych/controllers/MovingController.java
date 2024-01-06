package com.javarush.island.kotovych.controllers;

import com.javarush.island.kotovych.organisms.animals.Animal;
import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.game.Square;

import java.util.Arrays;

public class MovingController implements Controller{

    GameScene gameScene;

    public MovingController(GameScene gameScene){
        this.gameScene = gameScene;
    }
    @Override
    public void execute() {
        for(int x = 0; x < gameScene.getField().length; x++){
            Square[] col = gameScene.getField()[x];
            Arrays.stream(col)
                    .parallel()
                    .forEach(square -> {
                        square.getOrganismList().parallelStream()
                                .forEach(i -> {
                                    if(i instanceof Animal) {
                                        ((Animal) i).move(square, gameScene);
                                    }
                                });
                    });
        }
    }

    @Override
    public void run() {
        Thread.currentThread().setName(this.getClass().getSimpleName());
        execute();
    }
}
