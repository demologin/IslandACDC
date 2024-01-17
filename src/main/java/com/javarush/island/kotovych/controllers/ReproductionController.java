package com.javarush.island.kotovych.controllers;

import com.javarush.island.kotovych.organisms.Flock;
import com.javarush.island.kotovych.organisms.Organism;
import com.javarush.island.kotovych.organisms.animals.Animal;
import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.game.Square;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RecursiveAction;
import java.util.stream.IntStream;

public class ReproductionController implements Controller {

    private final GameScene gameScene;
    private static List<Animal> reproducedAnimals = new CopyOnWriteArrayList<>();

    public ReproductionController(GameScene gameScene) {
        this.gameScene = gameScene;
    }

    @Override
    public void run() {
        reproducedAnimals = new CopyOnWriteArrayList<>();
        Arrays.stream(gameScene.getField())
                .flatMap(Arrays::stream)
                .parallel()
                .forEach(square -> {
                    List<Flock> flocks = square.getFlockList();
                    flocks.parallelStream()
                            .filter(flock -> flock.getOrganisms().size() > 1)
                            .forEach(flock -> {
                                List<Organism> animals = flock.getOrganisms();
                                animals.stream()
                                        .map(organism -> (Animal) organism)
                                        .filter(animal -> !reproducedAnimals.contains(animal))
                                        .forEach(animal -> {
                                            animal.reproduce(square, flock, reproducedAnimals);
                                            reproducedAnimals.add(animal);
                                        });
                            });
                });
    }
}
