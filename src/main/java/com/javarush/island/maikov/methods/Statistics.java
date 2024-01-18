package com.javarush.island.maikov.methods;

import com.javarush.island.maikov.Abstraction.Organism;
import com.javarush.island.maikov.Animals.Herbivore.*;
import com.javarush.island.maikov.Animals.Predators.*;
import com.javarush.island.maikov.Grass.Clover;

import java.util.concurrent.atomic.AtomicInteger;

public class Statistics {
    protected static volatile AtomicInteger countBoar = new AtomicInteger(0);
    protected static volatile AtomicInteger countBuffalo = new AtomicInteger(0);
    protected static volatile AtomicInteger countCaterpillar = new AtomicInteger(0);
    protected static volatile AtomicInteger countDeer = new AtomicInteger(0);
    protected static volatile AtomicInteger countDuck = new AtomicInteger(0);
    protected static volatile AtomicInteger countGoat = new AtomicInteger(0);
    protected static volatile AtomicInteger countHorse = new AtomicInteger(0);
    protected static volatile AtomicInteger countMouse = new AtomicInteger(0);
    protected static volatile AtomicInteger countRabbit = new AtomicInteger(0);
    protected static volatile AtomicInteger countSheep = new AtomicInteger(0);

    protected static volatile AtomicInteger countBear = new AtomicInteger(0);
    protected static volatile AtomicInteger countBoa = new AtomicInteger(0);
    protected static volatile AtomicInteger countEagle = new AtomicInteger(0);
    protected static volatile AtomicInteger countFox = new AtomicInteger(0);
    protected static volatile AtomicInteger countWolf = new AtomicInteger(0);
    protected static volatile AtomicInteger countClover = new AtomicInteger(0);
    protected static volatile AtomicInteger countMoving = new AtomicInteger(0);
    protected static volatile AtomicInteger countEating = new AtomicInteger(0);
    protected static volatile AtomicInteger countReproduce = new AtomicInteger(0);


    public void removeFromStatistics(Organism someOrganism) {
        if (someOrganism instanceof Boar) {
            countBoar.decrementAndGet();
        }
        if (someOrganism instanceof Buffalo) {
            countBuffalo.decrementAndGet();
        }
        if (someOrganism instanceof Caterpillar) {
            countCaterpillar.decrementAndGet();
        }
        if (someOrganism instanceof Deer) {
            countDeer.decrementAndGet();
        }
        if (someOrganism instanceof Duck) {
            countDuck.decrementAndGet();
        }
        if (someOrganism instanceof Goat) {
            countGoat.decrementAndGet();
        }
        if (someOrganism instanceof Horse) {
            countHorse.decrementAndGet();
        }
        if (someOrganism instanceof Mouse) {
            countMouse.decrementAndGet();
        }
        if (someOrganism instanceof Rabbit) {
            countRabbit.decrementAndGet();
        }
        if (someOrganism instanceof Sheep) {
            countSheep.decrementAndGet();
        }
        if (someOrganism instanceof Bear) {
            countBear.decrementAndGet();
        }
        if (someOrganism instanceof Boa) {
            countBoa.decrementAndGet();
        }
        if (someOrganism instanceof Eagle) {
            countEagle.decrementAndGet();
        }
        if (someOrganism instanceof Fox) {
            countFox.decrementAndGet();
        }
        if (someOrganism instanceof Wolf) {
            countWolf.decrementAndGet();
        }
        if (someOrganism instanceof Clover) {
            countClover.decrementAndGet();
        }
    }

    public void addToStatistics(Organism someOrganism) {
        if (someOrganism instanceof Boar) {
            countBoar.incrementAndGet();
        }
        if (someOrganism instanceof Buffalo) {
            countBuffalo.incrementAndGet();
        }
        if (someOrganism instanceof Caterpillar) {
            countCaterpillar.incrementAndGet();
        }
        if (someOrganism instanceof Deer) {
            countDeer.incrementAndGet();
        }
        if (someOrganism instanceof Duck) {
            countDuck.incrementAndGet();
        }
        if (someOrganism instanceof Goat) {
            countGoat.incrementAndGet();
        }
        if (someOrganism instanceof Horse) {
            countHorse.incrementAndGet();
        }
        if (someOrganism instanceof Mouse) {
            countMouse.incrementAndGet();
        }
        if (someOrganism instanceof Rabbit) {
            countRabbit.incrementAndGet();
        }
        if (someOrganism instanceof Sheep) {
            countSheep.incrementAndGet();
        }
        if (someOrganism instanceof Bear) {
            countBear.incrementAndGet();
        }
        if (someOrganism instanceof Boa) {
            countBoa.incrementAndGet();
        }
        if (someOrganism instanceof Eagle) {
            countEagle.incrementAndGet();
        }
        if (someOrganism instanceof Fox) {
            countFox.incrementAndGet();
        }
        if (someOrganism instanceof Wolf) {
            countWolf.incrementAndGet();
        }
        if (someOrganism instanceof Clover) {
            countClover.incrementAndGet();
        }
    }
    public void countMoving(){
        countMoving.incrementAndGet();
    }
    public void countEating(){
        countEating.incrementAndGet();
    }
    public void countReproduce(){
        countReproduce.incrementAndGet();
    }
}
