package com.javarush.island.maikov.methods;

import com.javarush.island.maikov.Abstraction.Organism;
import com.javarush.island.maikov.Organism.Grass.Clover;
import com.javarush.island.maikov.Organism.Herbivore.*;
import com.javarush.island.maikov.Organism.Predators.*;

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
    protected static volatile AtomicInteger countDeath = new AtomicInteger(0);
    // value public for using in PrintToConsole
    public static volatile AtomicInteger countPredators = new AtomicInteger(0);
    public static volatile AtomicInteger countHerbivores = new AtomicInteger(0);


    public void removeFromStatistics(Organism someOrganism) {
        if (someOrganism instanceof Boar) {
            countBoar.decrementAndGet();
            countHerbivores.decrementAndGet();
        }
        if (someOrganism instanceof Buffalo) {
            countBuffalo.decrementAndGet();
            countHerbivores.decrementAndGet();
        }
        if (someOrganism instanceof Caterpillar) {
            countCaterpillar.decrementAndGet();
            countHerbivores.decrementAndGet();
        }
        if (someOrganism instanceof Deer) {
            countDeer.decrementAndGet();
            countHerbivores.decrementAndGet();
        }
        if (someOrganism instanceof Duck) {
            countDuck.decrementAndGet();
            countHerbivores.decrementAndGet();
        }
        if (someOrganism instanceof Goat) {
            countGoat.decrementAndGet();
            countHerbivores.decrementAndGet();
        }
        if (someOrganism instanceof Horse) {
            countHorse.decrementAndGet();
            countHerbivores.decrementAndGet();
        }
        if (someOrganism instanceof Mouse) {
            countMouse.decrementAndGet();
            countHerbivores.decrementAndGet();
        }
        if (someOrganism instanceof Rabbit) {
            countRabbit.decrementAndGet();
            countHerbivores.decrementAndGet();
        }
        if (someOrganism instanceof Sheep) {
            countSheep.decrementAndGet();
            countHerbivores.decrementAndGet();
        }
        if (someOrganism instanceof Bear) {
            countBear.decrementAndGet();
            countPredators.decrementAndGet();
        }
        if (someOrganism instanceof Boa) {
            countBoa.decrementAndGet();
            countPredators.decrementAndGet();
        }
        if (someOrganism instanceof Eagle) {
            countEagle.decrementAndGet();
            countPredators.decrementAndGet();
        }
        if (someOrganism instanceof Fox) {
            countFox.decrementAndGet();
            countPredators.decrementAndGet();
        }
        if (someOrganism instanceof Wolf) {
            countWolf.decrementAndGet();
            countPredators.decrementAndGet();
        }
        if (someOrganism instanceof Clover) {
            countClover.decrementAndGet();
        }
    }

    public void addToStatistics(Organism someOrganism) {
        if (someOrganism instanceof Boar) {
            countBoar.incrementAndGet();
            countHerbivores.incrementAndGet();
        }
        if (someOrganism instanceof Buffalo) {
            countBuffalo.incrementAndGet();
            countHerbivores.incrementAndGet();
        }
        if (someOrganism instanceof Caterpillar) {
            countCaterpillar.incrementAndGet();
            countHerbivores.incrementAndGet();
        }
        if (someOrganism instanceof Deer) {
            countDeer.incrementAndGet();
            countHerbivores.incrementAndGet();
        }
        if (someOrganism instanceof Duck) {
            countDuck.incrementAndGet();
            countHerbivores.incrementAndGet();
        }
        if (someOrganism instanceof Goat) {
            countGoat.incrementAndGet();
            countHerbivores.incrementAndGet();
        }
        if (someOrganism instanceof Horse) {
            countHorse.incrementAndGet();
            countHerbivores.incrementAndGet();
        }
        if (someOrganism instanceof Mouse) {
            countMouse.incrementAndGet();
            countHerbivores.incrementAndGet();
        }
        if (someOrganism instanceof Rabbit) {
            countRabbit.incrementAndGet();
            countHerbivores.incrementAndGet();
        }
        if (someOrganism instanceof Sheep) {
            countSheep.incrementAndGet();
            countHerbivores.incrementAndGet();
        }
        if (someOrganism instanceof Bear) {
            countBear.incrementAndGet();
            countPredators.incrementAndGet();
        }
        if (someOrganism instanceof Boa) {
            countBoa.incrementAndGet();
            countPredators.incrementAndGet();
        }
        if (someOrganism instanceof Eagle) {
            countEagle.incrementAndGet();
            countPredators.incrementAndGet();
        }
        if (someOrganism instanceof Fox) {
            countFox.incrementAndGet();
            countPredators.incrementAndGet();
        }
        if (someOrganism instanceof Wolf) {
            countWolf.incrementAndGet();
            countPredators.incrementAndGet();
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
    public void countDeath(){
        countDeath.incrementAndGet();
    }
}
