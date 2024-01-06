package com.javarush.island.kotovych.controllers;

import com.javarush.island.kotovych.organisms.Organism;
import com.javarush.island.kotovych.organisms.animals.Animal;
import com.javarush.island.kotovych.scene.GameScene;
import com.javarush.island.kotovych.scene.Square;

import java.util.concurrent.ThreadLocalRandom;

public class DiseaseSpreadingController implements Controller {
    GameScene gameScene;

    public DiseaseSpreadingController(GameScene gameScene) {
        this.gameScene = gameScene;
    }

    @Override
    public void execute() {
        int randomX = ThreadLocalRandom.current().nextInt(0, gameScene.getWidth());
        int randomY = ThreadLocalRandom.current().nextInt(0, gameScene.getHeight());

        Square randomSquare = gameScene.getSquareByCoordinates(randomX, randomY);
        int randomAnimalIndex = ThreadLocalRandom.current().nextInt(randomSquare.getOrganismList().size());
        Organism randomAnimal = randomSquare.getOrganismList().get(randomAnimalIndex);

    }

    @Override
    public void run() {
        Thread.currentThread().setName(this.getClass().getSimpleName());
        execute();
    }
}
