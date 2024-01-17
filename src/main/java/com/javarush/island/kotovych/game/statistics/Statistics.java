package com.javarush.island.kotovych.game.statistics;

import com.javarush.island.kotovych.game.GameScene;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class Statistics {
    private GameScene gameScene;

    private AtomicInteger totalOrganisms = new AtomicInteger(0);
    private Map<String, AtomicInteger> totalOrganismCount = new ConcurrentHashMap<>();
    public Statistics(GameScene gameScene){
        this.gameScene = gameScene;
    }
}
