package com.javarush.island.berezovskiy.entities.organism.animals.herbivores;

import com.javarush.island.berezovskiy.constants.Constants;
import com.javarush.island.berezovskiy.entities.organism.animals.Animal;


public abstract class Herbivorous extends Animal {
    protected Herbivorous() {
        organismType = Constants.HERBIVORE;
    }
}
