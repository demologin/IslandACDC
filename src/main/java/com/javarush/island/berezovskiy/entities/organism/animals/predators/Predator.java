package com.javarush.island.berezovskiy.entities.organism.animals.predators;

import com.javarush.island.berezovskiy.constants.Constants;
import com.javarush.island.berezovskiy.entities.organism.animals.Animal;

public abstract class Predator extends Animal {
    protected Predator(){
        organismType = Constants.PREDATOR;
    }
}
