package com.javarush.island.maikov.Animals.Predators;

import com.javarush.island.maikov.Abstraction.Predator;
import com.javarush.island.maikov.Constants;

public class Boa extends Predator {

    public Boa(int x, int y) {
        super(15, 30, 1, 3, x, y);
    }

    @Override
    public String toString() {
        return Constants.BOA;
    }
}
