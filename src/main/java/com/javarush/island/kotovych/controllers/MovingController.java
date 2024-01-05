package com.javarush.island.kotovych.controllers;

import com.javarush.island.kotovych.organisms.animals.Animal;
import com.javarush.island.kotovych.scene.GameScene;
import com.javarush.island.kotovych.scene.Square;
import lombok.Getter;

import java.util.Arrays;

public class MovingController implements Controller, Runnable{

    GameScene gameScene;

    @Getter
    private int delay;
    public MovingController(GameScene gameScene){
        this.gameScene = gameScene;
    }
    @Override
    public void execute() {
        for(int y = 0; y < gameScene.getField().length; y++){
            Square[] col = gameScene.getField()[y];
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
