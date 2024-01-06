package com.javarush.island.kotovych.controllers;

import com.javarush.island.kotovych.organisms.animals.Animal;
import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.game.Square;
import com.javarush.island.kotovych.util.OrganismDataTable;

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
                    .forEach(square -> square.getOrganismList().parallelStream()
                            .forEach(i -> {
                                if(i.getWeight() < OrganismDataTable.getData(i).get("weight") * 0.6) {
                                    if (i instanceof Animal) {
                                        ((Animal) i).eat(square);
                                    }
                                }
                            }));
        }
    }

    @Override
    public void run() {
        Thread.currentThread().setName(this.getClass().getSimpleName());
        execute();
    }
}
