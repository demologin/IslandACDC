package com.javarush.island.maikov;

import com.javarush.island.maikov.Abstraction.Organism;
import com.javarush.island.maikov.Animals.Herbivore.Rabbit;
import com.javarush.island.maikov.Animals.Predators.Wolf;
import com.javarush.island.maikov.methods.Print;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class MapOfIsland {
    public static final ArrayList<Organism>[][] mapOfIsland = new ArrayList[1][2];

    public MapOfIsland() {
        ReentrantLock reentrantLock = new ReentrantLock();
        try {
            reentrantLock.lock();
            for (int i = 0; i < mapOfIsland.length; i++) {
                for (int j = 0; j < mapOfIsland[i].length; j++) {
                    int countOfPredators = ThreadLocalRandom.current().nextInt(2, 10);
                    int countOfHerbivores = ThreadLocalRandom.current().nextInt(2, 10);
                    mapOfIsland[i][j] = new ArrayList<>();
                    for (int herb = 0; herb < countOfHerbivores; herb++) {
                        mapOfIsland[i][j].add(new Rabbit(i, j));

                    }
                    for (int pred = 0; pred < countOfPredators; pred++) {
                        mapOfIsland[i][j].add(new Wolf(i, j));
                    }

                }
            }
        } finally {
            reentrantLock.unlock();
        }
        System.out.println("Animals map at the start");
        while (true) {
                Print.print(mapOfIsland);
                System.out.println("=================================");
                try {
                    Thread.sleep(5000);
                }
            catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        }

    }
}
