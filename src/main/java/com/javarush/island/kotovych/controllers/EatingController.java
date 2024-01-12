package com.javarush.island.kotovych.controllers;

import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.game.Square;
import com.javarush.island.kotovych.organisms.animals.Animal;
import com.javarush.island.kotovych.util.OrganismDataTable;

import java.util.Arrays;
import java.util.stream.IntStream;

public class EatingController implements Controller {

    private final GameScene gameScene;

    public EatingController(GameScene gameScene){
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
                                    .filter(i -> i.getWeight() < OrganismDataTable.getData(i).get("weight") * 0.6)
                                    .filter(i -> i instanceof Animal)
                                    .map(i -> (Animal) i)
                                    .forEach(animal -> animal.eat(square)));
                });
    }
}
