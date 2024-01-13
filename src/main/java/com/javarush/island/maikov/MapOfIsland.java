package com.javarush.island.maikov;

import com.javarush.island.maikov.Abstraction.Organism;
import com.javarush.island.maikov.Animals.Herbivore.Rabbit;
import com.javarush.island.maikov.Animals.Predators.Wolf;
import com.javarush.island.maikov.Grass.Clover;
import com.javarush.island.maikov.methods.Print;
import com.javarush.island.maikov.methods.Statistics;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class MapOfIsland {
    public static final ArrayList<Organism>[][] mapOfIsland = new ArrayList[3][3];

    public MapOfIsland() {
        ReentrantLock reentrantLock = new ReentrantLock();
        try {
            reentrantLock.lock();
            createOrganismsAtStart();
        } finally {
            reentrantLock.unlock();
        }
        System.out.println("Loading animals map at the start.Please wait.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Возникла проблема при загрузке");
        }
        Print print = new Print();
        while (true) {
                print.printToConsole(mapOfIsland);
                System.out.println("=================================");
                try {
                    Thread.sleep(5000);
                }
            catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        }

    }
    private void createOrganismsAtStart() {
        Statistics statistics = new Statistics();
        for (int i = 0; i < mapOfIsland.length; i++) {
            for (int j = 0; j < mapOfIsland[i].length; j++) {
                int countOfPredators = ThreadLocalRandom.current().nextInt(2, 10);
                int countOfHerbivores = ThreadLocalRandom.current().nextInt(2, 10);
                int countOfClover = ThreadLocalRandom.current().nextInt(0, 5);
                mapOfIsland[i][j] = new ArrayList<>();
                for (int herb = 0; herb < countOfHerbivores; herb++) {
                    Rabbit newRabbit = new Rabbit(i, j);
                    mapOfIsland[i][j].add(newRabbit);
                    statistics.addToStatistics(newRabbit);

                }
                for (int pred = 0; pred < countOfPredators; pred++) {
                    Wolf newWolf = new Wolf(i, j);
                    mapOfIsland[i][j].add(newWolf);
                    statistics.addToStatistics(newWolf);
                }
                for (int grass = 0; grass < countOfClover; grass++) {
                    Clover newClover = new Clover(i, j);
                    mapOfIsland[i][j].add(newClover);
                    statistics.addToStatistics(newClover);
                }
            }
        }
    }
}
