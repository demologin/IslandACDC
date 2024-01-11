package com.javarush.island.maikov.methods;

import com.javarush.island.maikov.Abstraction.Organism;
import com.javarush.island.maikov.Animals.Herbivore.Rabbit;
import com.javarush.island.maikov.Animals.Predators.Wolf;
import com.javarush.island.maikov.Constants;
import com.javarush.island.maikov.MapOfIsland;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Print {

    public static void print(ArrayList<Organism>[][] mapOfIsland) {
            for (int i = 0; i < mapOfIsland.length; i++) {
                for (int j = 0; j < mapOfIsland[i].length; j++) {
                    for (int k = 0; k < mapOfIsland[i][j].size(); k++) {
                        System.out.print(mapOfIsland[i][j].get(k));
                    }
                    System.out.print("||");
                }
                System.out.println();
            }
        System.out.printf("Wolf = %d, Rabbit = %d, Clover = %d%n", Statistics.countWolf, Statistics.countRabbit,
                Statistics.countClover);
    }
}