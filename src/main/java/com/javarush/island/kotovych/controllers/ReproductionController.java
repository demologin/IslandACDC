package com.javarush.island.kotovych.controllers;

import com.javarush.island.kotovych.organisms.animals.Animal;
import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.game.Square;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
import java.util.stream.IntStream;

public class ReproductionController implements Controller {

    private final GameScene gameScene;

    public ReproductionController(GameScene gameScene){
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
                                    .filter(i -> i instanceof Animal)
                                    .map(i -> (Animal) i)
                                    .forEach(animal -> animal.reproduce(square)));
                });
    }
}
