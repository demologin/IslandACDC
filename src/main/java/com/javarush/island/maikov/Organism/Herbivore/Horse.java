package com.javarush.island.maikov.Organism.Herbivore;

import com.javarush.island.maikov.Abstraction.Herbivore;
import com.javarush.island.maikov.Constants.Constants;

public class Horse extends Herbivore {
    public Horse(int x, int y) {
        super(400,20,4,60, x, y);
    }

    @Override
    public String toString() {
        return Constants.HORSE;
    }
}
