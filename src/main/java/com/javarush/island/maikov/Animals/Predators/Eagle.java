package com.javarush.island.maikov.Animals.Predators;

import com.javarush.island.maikov.Abstraction.Predator;
import com.javarush.island.maikov.Constants;

public class Eagle extends Predator {
    public Eagle(int x, int y) {
        super(6, 20, 3, 1, x, y);
    }

    @Override
    public String toString() {
        return Constants.EAGLE;
    }
}
