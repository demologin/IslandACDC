package com.javarush.island.kotovych.controllers;

import com.javarush.island.kotovych.organisms.animals.Animal;
import com.javarush.island.kotovych.scene.GameScene;
import com.javarush.island.kotovych.scene.Square;

import java.util.Arrays;

public class EatingController implements Controller{
    GameScene gameScene;
    public EatingController(GameScene gameScene){
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
                                .forEach(i -> ((Animal) i).eat(square));
                    });
        }
    }
}
