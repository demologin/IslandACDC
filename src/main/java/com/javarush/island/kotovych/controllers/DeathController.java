package com.javarush.island.kotovych.controllers;

import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.game.Square;
import com.javarush.island.kotovych.organisms.Organism;
import com.javarush.island.kotovych.util.OrganismDataTable;

import java.util.Arrays;
import java.util.stream.IntStream;

public class DeathController implements Controller {

    private final GameScene gameScene;

    public DeathController(GameScene gameScene) {
        this.gameScene = gameScene;
    }

    @Override
    public void run() {
        IntStream.range(0, gameScene.getField().length)
                .parallel()
                .forEach(y -> {
                    Square[] column = gameScene.getField()[y];

                    Arrays.stream(column)
                            .parallel()
                            .forEach(square -> square.getOrganismList().parallelStream()
                                    .filter(this::shouldDie)
                                    .forEach(organism -> organism.die(square)));
                });


    }

    private boolean shouldDie(Organism organism) {
        return organism.getWeight() <= OrganismDataTable.getData(organism).get("weight") * 0.3;
    }
}
