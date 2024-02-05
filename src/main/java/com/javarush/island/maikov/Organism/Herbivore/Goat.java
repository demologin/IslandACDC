package com.javarush.island.maikov.Organism.Herbivore;

import com.javarush.island.maikov.Abstraction.Herbivore;
import com.javarush.island.maikov.Constants.Constants;

public class Goat extends Herbivore {
    public Goat(int x, int y) {
        super(60, 140, 3, 10, x, y);
    }

    @Override
    public String toString() {
        return Constants.GOAT;
    }
}
