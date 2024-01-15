package com.javarush.island.maikov.Animals.Predators;

import com.javarush.island.maikov.Abstraction.Predator;
import com.javarush.island.maikov.Constants;

public class Bear extends Predator {
    public Bear(int x, int y) {
        super(500, 5, 2, 80, x, y);
    }

    @Override
    public String toString() {
        return Constants.BEAR;
    }
}
