package com.javarush.island.maikov.Animals.Herbivore;

import com.javarush.island.maikov.Constants;

public class Deer extends Herbivore{
    public Deer(int x, int y) {
        super(300,200,4,50, x, y);
    }

    @Override
    public String toString() {
        return Constants.DEER;
    }
}
