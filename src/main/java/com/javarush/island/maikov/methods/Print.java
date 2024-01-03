package com.javarush.island.maikov.methods;

import com.javarush.island.maikov.Abstraction.Organism;
import com.javarush.island.maikov.Animals.Herbivore.Rabbit;
import com.javarush.island.maikov.Animals.Predators.Wolf;
import com.javarush.island.maikov.Constants;

import java.util.ArrayList;

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
    }
}
