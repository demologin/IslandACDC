package com.javarush.island.maikov.Organism.Herbivore;

import com.javarush.island.maikov.Abstraction.Herbivore;
import com.javarush.island.maikov.Constants.Constants;

public class Caterpillar extends Herbivore {
    public Caterpillar(int x, int y) {
        super(1, 200, 0, 0, x, y);
    }

    @Override
    public String toString() {
        return Constants.CATERPILLAR;
    }
}
