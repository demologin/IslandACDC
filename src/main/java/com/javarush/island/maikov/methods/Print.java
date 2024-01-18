package com.javarush.island.maikov.methods;

import com.javarush.island.maikov.Abstraction.Organism;

import java.util.Arrays;
import java.util.HashSet;

public class Print {

    public void printToConsole(HashSet<Organism>[][] mapOfIsland) {
        System.out.println(Arrays.deepToString(mapOfIsland));
//            for (int i = 0; i < mapOfIsland.length; i++) {
//                for (int j = 0; j < mapOfIsland[i].length; j++) {
//                    for (int k = 0; k < mapOfIsland[i][j].size(); k++) {
//                        System.out.print(mapOfIsland[i][j].);
//                    }
//                    System.out.print("||");
//                }
//                System.out.println();
//            }
        System.out.println("Boar = " + Statistics.countBoar + " Buffalo = " + Statistics.countBuffalo +
                " Caterpillar = " + Statistics.countCaterpillar + " Deer = " + Statistics.countDeer +
                " Duck = " + Statistics.countDuck + " Goat = " + Statistics.countGoat
                + " Horse = " + Statistics.countHorse + " Mouse = " + Statistics.countMouse +
                " Rabbit = " + Statistics.countRabbit + " Sheep = " + Statistics.countSheep +
                " Bear = " + Statistics.countBear + " Boa = " + Statistics.countBoa +
                " Eagle = " + Statistics.countEagle + " Fox = " + Statistics.countFox +
                " Wolf = " + Statistics.countWolf + " Clover = " + Statistics.countClover);
        System.out.println("Count moving = " + Statistics.countMoving +
                " Count eating = " + Statistics.countEating +
                " Count reproduce = " + Statistics.countReproduce);
    }
}