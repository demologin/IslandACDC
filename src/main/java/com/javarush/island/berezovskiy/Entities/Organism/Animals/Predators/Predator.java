package com.javarush.island.berezovskiy.Entities.Organism.Animals.Predators;

import com.javarush.island.berezovskiy.Constants.Constants;
import com.javarush.island.berezovskiy.Entities.Organism.Animals.Animal;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Predator extends Animal {
    protected Predator(){
        organismType = Constants.PREDATOR;
    }
}
