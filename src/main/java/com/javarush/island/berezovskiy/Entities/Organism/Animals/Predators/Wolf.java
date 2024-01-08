package com.javarush.island.berezovskiy.Entities.Organism.Animals.Predators;

import com.javarush.island.berezovskiy.Entities.Organism.Animals.Animal;
import com.javarush.island.berezovskiy.Entities.Organism.Organism;

import java.util.concurrent.atomic.AtomicInteger;

public class Wolf extends Predator{
    protected static AtomicInteger wolfNumber = new AtomicInteger();
    protected static int increaseOrganismNumber() {
        return wolfNumber.incrementAndGet();
    }

    public Wolf(){
        Organism.increaseOrganismNumber();
        Animal.increaseOrganismNumber();
        Predator.increaseOrganismNumber();
        increaseOrganismNumber();
    }
    @Override
    public void eat() {

    }

    @Override
    public void move() {

    }

    @Override
    public void reproduce() {

    }
}
