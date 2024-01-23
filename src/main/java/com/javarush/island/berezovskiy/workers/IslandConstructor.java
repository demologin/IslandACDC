package com.javarush.island.berezovskiy.workers;

import com.javarush.island.berezovskiy.entities.Island;
import com.javarush.island.berezovskiy.executor.IslandSimulationExecutor;

public class IslandConstructor {
    public IslandConstructor() {
    }
    public void startSimulation() {
        Island island = new Island();
        IslandSimulationExecutor islandSimulationExecutor = new IslandSimulationExecutor(island);
        islandSimulationExecutor.startExecutor();
    }

}
