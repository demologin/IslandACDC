package com.javarush.island.maikov.Animals.Herbivore;

import com.javarush.island.maikov.Constants;

public class Boar extends Herbivore{
    public Boar(int x, int y) {
        super(400,50,2,50, x, y);
    }

    @Override
    public String toString() {
        return Constants.BOAR;
    }
}
