package com.javarush.island.kotovych.controllers;

import com.javarush.island.kotovych.factory.OrganismFactory;
import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.organisms.Flock;
import com.javarush.island.kotovych.settings.Settings;
import com.javarush.island.kotovych.util.Rnd;

import java.util.Arrays;

public class RandomAnimalGeneratorController implements Controller{

    GameScene gameScene;
    public RandomAnimalGeneratorController(GameScene gameScene){
        this.gameScene = gameScene;
    }

    @Override
    public void run() {
        Arrays.stream(gameScene.getField())
                .flatMap(Arrays::stream)
                .parallel()
                .forEach(square -> {
                    int number = Rnd.nextInt(10);
                    Object[] organisms = OrganismFactory.getOrganismPrototypes().keySet().toArray();
                    int randomOrganismNumber = Rnd.nextInt(organisms.length);
                    if(number == 1){
                        int organismsInFlock = Rnd.nextInt(Settings.getMaxFlockSize());
                        square.addFlock(new Flock((String) organisms[randomOrganismNumber], organismsInFlock));
                    }
                });
    }
}
