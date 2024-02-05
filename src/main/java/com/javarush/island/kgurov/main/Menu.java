package com.javarush.island.kgurov.main;

import com.javarush.island.kgurov.field.GameMap;
import com.javarush.island.kgurov.simulation.IslandSimulation;

public class Menu {
    public void startSimulation() {
        System.out.println("----------------------------------");
        GameMap.getInstance();
        IslandSimulation.getInstance().createIslandModel();
    }
}
