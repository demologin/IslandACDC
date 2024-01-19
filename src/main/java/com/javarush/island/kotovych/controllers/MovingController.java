package com.javarush.island.kotovych.controllers;

import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.organisms.Flock;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MovingController implements Controller {
    private static List<Flock> movedFlocks = new CopyOnWriteArrayList<>();

    private final GameScene gameScene;

    public MovingController(GameScene gameScene){
        this.gameScene = gameScene;
    }

    @Override
    public void run() {
        movedFlocks = new CopyOnWriteArrayList<>();
        Arrays.stream(gameScene.getField())
                .flatMap(Arrays::stream)
                .parallel()
                .forEach(square -> {
                    List<Flock> flocks = square.getFlockList();
                    flocks.parallelStream()
                            .filter(flock -> !flock.getName().equals("Plant"))
                            .filter(flock -> !movedFlocks.contains(flock))
                            .forEach(flock -> {
                                flock.move(square, gameScene);
                                movedFlocks.add(flock);
                            });
                });
    }
}
