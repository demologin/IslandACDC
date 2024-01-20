package com.javarush.island.maikov.Organism.Herbivore;

import com.javarush.island.maikov.Abstraction.Herbivore;
import com.javarush.island.maikov.Constants.Constants;

public class Boar extends Herbivore {
    public Boar(int x, int y) {
        super(400,50,2,50, x, y);
    }

    @Override
    public String toString() {
        return Constants.BOAR;
    }
}
