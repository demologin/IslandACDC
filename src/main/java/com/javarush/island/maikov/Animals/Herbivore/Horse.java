package com.javarush.island.maikov.Animals.Herbivore;

import com.javarush.island.maikov.Constants;

public class Horse extends Herbivore{
    public Horse(int x, int y) {
        super(400,20,4,60, x, y);
    }

    @Override
    public String toString() {
        return Constants.HORSE;
    }
}
