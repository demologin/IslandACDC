package com.javarush.island.khasanov.service;

import com.javarush.island.khasanov.config.Setting;
import com.javarush.island.khasanov.repository.Island;


import com.javarush.island.khasanov.repository.IslandObjects;
import com.javarush.island.khasanov.repository.Position;
import lombok.Getter;

import java.util.Map;

@Getter
public class Simulation {
    private final Island island;

    public Simulation() {
        this.island = new Island(Setting.width, Setting.height);
    }

    public void run() {
        island.fill();
        printIsland();
        printStats();
        doStep();
        printStats();
        printIsland();
    }

    private void doStep() {
        island.letAnimalsEat();
        //island.reproduceAndGrow();
        island.moveIslandObjects();
    }

    private void printStats(){
        System.out.println("-".repeat(60));
        System.out.println("\nStats");
        for (Map.Entry<Position, Map<IslandObjects, Integer>> positionMapEntry : island.getCountObjectOnField().entrySet()) {
            for (Map.Entry<IslandObjects, Integer> entry : positionMapEntry.getValue().entrySet()) {
                Integer count = entry.getValue();
                if (count > 0) {
                    System.out.printf("%s : %s = %d\n",
                            positionMapEntry.getKey(),
                            entry.getKey(),
                            count
                            );
                }
            }
        }
        System.out.println("-".repeat(60));
    }



    private void printIsland() {
        System.out.println("-".repeat(60));
        island.getIslandMap().forEach((key, value) -> System.out.println(key + " " + value));
        System.out.println("-".repeat(60));
    }

}
