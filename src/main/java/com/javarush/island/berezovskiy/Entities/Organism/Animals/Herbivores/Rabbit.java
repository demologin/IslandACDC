package com.javarush.island.berezovskiy.Entities.Organism.Animals.Herbivores;

import com.javarush.island.berezovskiy.Entities.Organism.Animals.Animal;
import com.javarush.island.berezovskiy.Entities.Organism.Organism;

import java.util.concurrent.atomic.AtomicInteger;

public class Rabbit extends Herbivorous{

    protected static AtomicInteger rabbitNumber = new AtomicInteger();
    protected static int increaseOrganismNumber() {
        return rabbitNumber.incrementAndGet();
    }
    public Rabbit(){
        Organism.increaseOrganismNumber();
        Animal.increaseOrganismNumber();
        Herbivorous.increaseOrganismNumber();
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

    public void setCell(){

    }
}
