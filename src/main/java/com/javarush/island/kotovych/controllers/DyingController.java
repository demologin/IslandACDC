package com.javarush.island.kotovych.controllers;

import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.game.Square;
import com.javarush.island.kotovych.util.OrganismDataTable;

import java.util.Arrays;

public class DyingController implements Controller{
    GameScene gameScene;

    public DyingController(GameScene gameScene){
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
                                if(i.getHealth() <= 5 || i.getWeight() <= OrganismDataTable.getData(i).get("weight") * 0.3){
                                    i.die(square);
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
