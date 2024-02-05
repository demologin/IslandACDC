package com.javarush.island.kotovych.controllers;

import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.organisms.Flock;
import java.util.Arrays;
import java.util.List;

public class EatingController implements Controller {

    private final GameScene gameScene;

    public EatingController(GameScene gameScene) {
        this.gameScene = gameScene;
    }


    @Override
    public void run() {
        Arrays.stream(gameScene.getField())
                .flatMap(Arrays::stream)
                .parallel()
                .forEach(square -> {
                    List<Flock> flocks = square.getFlockList();
                    flocks.parallelStream()
                            .filter(flock -> !flock.isBlocked())
                            .filter(flock -> !flock.getName().equals("Plant"))
                            .forEach(flock -> flock.eat(square));
                });

    }
}
