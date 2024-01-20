package com.javarush.island.maikov.Organism.Predators;

import com.javarush.island.maikov.Abstraction.Predator;
import com.javarush.island.maikov.Constants.Constants;

public class Fox extends Predator {

    public Fox(int x, int y) {
        super(8, 30, 2, 2, x, y);
    }

    @Override
    public String toString() {
        return Constants.FOX;
    }
}
