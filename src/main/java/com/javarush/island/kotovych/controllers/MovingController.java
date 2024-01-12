package com.javarush.island.kotovych.controllers;

import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.game.Square;
import com.javarush.island.kotovych.organisms.animals.Animal;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

public class MovingController implements Controller {
    private static CopyOnWriteArrayList<Animal> movedAnimals = new CopyOnWriteArrayList<>();

    private final GameScene gameScene;

    public MovingController(GameScene gameScene){
        this.gameScene = gameScene;
    }

    @Override
    public void run() {
        movedAnimals = new CopyOnWriteArrayList<>();
        IntStream.range(0, gameScene.getField().length)
                .parallel()
                .forEach(x -> {
                    Square[] column = gameScene.getField()[x];
                    Arrays.stream(column)
                            .parallel()
                            .forEach(square -> {
                                square.getOrganismList().parallelStream()
                                        .filter(organism -> organism instanceof Animal)
                                        .map(organism -> (Animal) organism)
                                        .filter(animal -> !movedAnimals.contains(animal))
                                        .forEach(animal -> {
                                            animal.move(square, gameScene);
                                            movedAnimals.add(animal);
                                        });
                            });
                });
    }
}
