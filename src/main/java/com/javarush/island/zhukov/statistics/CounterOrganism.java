package com.javarush.island.zhukov.statistics;

import com.javarush.island.zhukov.constans.TextConstants;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterOrganism {
    public static volatile AtomicInteger countBull = new AtomicInteger(0);
    public static volatile AtomicInteger countHog = new AtomicInteger(0);
    public static volatile AtomicInteger countCaterpillar = new AtomicInteger(0);
    public static volatile AtomicInteger countDeer = new AtomicInteger(0);
    public static volatile AtomicInteger countDuck = new AtomicInteger(0);
    public static volatile AtomicInteger countGoat = new AtomicInteger(0);
    public static volatile AtomicInteger countHorse = new AtomicInteger(0);
    public static volatile AtomicInteger countMouse = new AtomicInteger(0);
    public static volatile AtomicInteger countRabbit = new AtomicInteger(0);
    public static volatile AtomicInteger countSheep = new AtomicInteger(0);
    public static volatile AtomicInteger countBear = new AtomicInteger(0);
    public static volatile AtomicInteger countEagle = new AtomicInteger(0);
    public static volatile AtomicInteger countFox = new AtomicInteger(0);
    public static volatile AtomicInteger countWolf = new AtomicInteger(0);
    public static volatile AtomicInteger countSnake = new AtomicInteger(0);
    public static volatile AtomicInteger countPlants = new AtomicInteger(0);
    public void printOrganism(){
        System.out.printf("""
                        %s: %d
                        %s:
                        \t%s: %d
                        \t%s: %d
                        \t%s: %d
                        \t%s: %d
                        \t%s: %d
                        \t%s: %d
                        \t%s: %d
                        \t%s: %d
                        \t%s: %d
                        \t%s: %d
                        %s:
                        \t%s: %d
                        \t%s: %d
                        \t%s: %d
                        \t%s: %d
                        \t%s: %d
                        """, TextConstants.PLANTS, countPlants.get(),
                TextConstants.HERBIVORES, TextConstants.BULL, countBull.get(),
                TextConstants.HOG, countHog.get(),
                TextConstants.CATERPILLARS, countCaterpillar.get(),
                TextConstants.DEER, countDeer.get(),
                TextConstants.DUCK, countDuck.get(),
                TextConstants.GOAT, countGoat.get(),
                TextConstants.HORSE, countHorse.get(),
                TextConstants.MOUSE, countMouse.get(),
                TextConstants.RABBIT, countRabbit.get(),
                TextConstants.SHEEP, countSheep.get(),
                TextConstants.PREDATORS,
                TextConstants.BEAR, countBear.get(),
                TextConstants.EAGLE, countEagle.get(),
                TextConstants.FOX, countFox.get(),
                TextConstants.WOLF, countWolf.get(),
                TextConstants.SNAKE, countSnake.get());
    }
}
