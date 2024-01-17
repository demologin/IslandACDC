package com.javarush.island.kotovych.controllers;

import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.game.Square;
import com.javarush.island.kotovych.organisms.Flock;
import com.javarush.island.kotovych.organisms.Organism;
import com.javarush.island.kotovych.organisms.animals.Animal;
import com.javarush.island.kotovych.settings.Settings;
import com.javarush.island.kotovych.util.OrganismDataTable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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
                            .filter(flock -> !flock.getName().equals("Plant"))
                            .forEach(flock -> flock.eat(square));
                });

    }
}
