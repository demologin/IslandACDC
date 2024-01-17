package com.javarush.island.kotovych.controllers;

import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.organisms.Flock;
import com.javarush.island.kotovych.organisms.Organism;
import com.javarush.island.kotovych.util.OrganismDataTable;

import java.util.Arrays;
import java.util.List;

public class DeathController implements Controller {

    private final GameScene gameScene;

    public DeathController(GameScene gameScene) {
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
                            .forEach(flock -> flock.getOrganisms().stream()
                                    .filter(this::shouldDie)
                                    .forEach(organism -> organism.die(flock, square)));
                });


    }

    private boolean shouldDie(Organism organism) {
        return organism.getWeight() <= OrganismDataTable.getData(organism.getName()).get("weight") * 0.3;
    }
}
