package com.javarush.island.maikov.Organism.Predators;

import com.javarush.island.maikov.Abstraction.Predator;
import com.javarush.island.maikov.Constants.Constants;

public class Boa extends Predator {

    public Boa(int x, int y) {
        super(15, 30, 1, 3, x, y);
    }

    @Override
    public String toString() {
        return Constants.BOA;
    }
}
