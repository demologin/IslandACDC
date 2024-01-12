package com.javarush.island.kotovych.controllers;

import com.javarush.island.kotovych.factory.OrganismFactory;
import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.game.Square;
import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class PlantGrowingController implements Controller {

    private final GameScene gameScene;

    public PlantGrowingController(GameScene gameScene){
        this.gameScene = gameScene;
    }

    @Override
    public void run() {
        IntStream.range(0, gameScene.getField().length)
                .parallel()
                .forEach(x -> {
                    Square[] column = gameScene.getField()[x];

                    Arrays.stream(column)
                            .parallel()
                            .forEach(square -> {
                                int randomNumber = ThreadLocalRandom.current().nextInt(2);
                                if (randomNumber == 1) {
                                    IntStream.range(0, 10)
                                            .forEach(i -> square.addOrganism(OrganismFactory.newOrganism("Plant")));
                                }
                            });
                });
    }
}
