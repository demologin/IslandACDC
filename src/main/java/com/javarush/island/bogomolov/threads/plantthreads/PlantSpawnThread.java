package com.javarush.island.bogomolov.threads.plantthreads;


import com.javarush.island.bogomolov.storage.IslandInitialization;




public class PlantSpawnThread implements Runnable {
    @Override
    public void run() {
        int countPlants = 20;
        if (IslandInitialization.getIslandInitialization().getCurrentTime() >= 2) {
            IslandInitialization.getIslandInitialization().placePlants(countPlants / 2);
        } else {
            IslandInitialization.getIslandInitialization().placePlants(countPlants);
        }
    }
}
