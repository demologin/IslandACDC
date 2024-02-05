package com.javarush.island.maikov.Organism.Herbivore;

import com.javarush.island.maikov.Abstraction.Herbivore;
import com.javarush.island.maikov.Constants.Constants;

public class Sheep extends Herbivore {
    public Sheep(int x, int y) {
        super(70, 140, 3, 15, x, y);
    }

    @Override
    public String toString() {
        return Constants.SHEEP;
    }
}
