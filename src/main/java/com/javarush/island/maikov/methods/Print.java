package com.javarush.island.maikov.methods;

import com.javarush.island.maikov.Abstraction.Organism;

import java.util.ArrayList;

public class Print {

    public void printToConsole(ArrayList<Organism>[][] mapOfIsland) {
            for (int i = 0; i < mapOfIsland.length; i++) {
                for (int j = 0; j < mapOfIsland[i].length; j++) {
                    for (int k = 0; k < mapOfIsland[i][j].size(); k++) {
                        System.out.print(mapOfIsland[i][j].get(k));
                    }
                    System.out.print("||");
                }
                System.out.println();
            }
        System.out.println("Boar = "+ Statistics.countBoar + " Buffalo = " + Statistics.countBuffalo +
                " Caterpillar = " + Statistics.countCaterpillar + " Deer = " + Statistics.countDeer +
                " Duck = " + Statistics.countDuck + " Goat = " + Statistics.countGoat
                + " Horse = " + Statistics.countHorse + " Mouse = " + Statistics.countMouse +
                " Rabbit = " + Statistics.countRabbit + " Sheep = " + Statistics.countSheep +
                " Bear = " + Statistics.countBear + " Boa = " + Statistics.countBoa +
                " Eagle = " + Statistics.countEagle + " Fox = " + Statistics.countFox +
                " Wolf = " + Statistics.countWolf + " Clover = " + Statistics.countClover);
    }
}