package com.javarush.island.maikov.methods;

import com.javarush.island.maikov.Abstraction.Organism;

import java.util.Arrays;
import java.util.HashSet;

public class PrintToConsole {

    public void printToConsole(HashSet<Organism>[][] mapOfIsland) {
        System.out.println(Arrays.deepToString(mapOfIsland));
        System.out.println("Boar = " + Statistics.countBoar + " Buffalo = " + Statistics.countBuffalo +
                " Caterpillar = " + Statistics.countCaterpillar + " Deer = " + Statistics.countDeer +
                " Duck = " + Statistics.countDuck + " Goat = " + Statistics.countGoat
                + " Horse = " + Statistics.countHorse + " Mouse = " + Statistics.countMouse +
                " Rabbit = " + Statistics.countRabbit + " Sheep = " + Statistics.countSheep +
                " Bear = " + Statistics.countBear + " Boa = " + Statistics.countBoa +
                " Eagle = " + Statistics.countEagle + " Fox = " + Statistics.countFox +
                "\nWolf = " + Statistics.countWolf + " Clover = " + Statistics.countClover);
        System.out.println("Count Herbivores = " + Statistics.countHerbivores
        + " Count Predators = " + Statistics.countPredators);
        System.out.println("Count moving = " + Statistics.countMoving +
                " Count eating = " + Statistics.countEating +
                " Count reproduce = " + Statistics.countReproduce +
                " Count Death = " + Statistics.countDeath);
    }
}