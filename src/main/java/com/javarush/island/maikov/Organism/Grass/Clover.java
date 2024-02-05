package com.javarush.island.maikov.Organism.Grass;


import com.javarush.island.maikov.Abstraction.Grass;
import com.javarush.island.maikov.Constants.Constants;

public class Clover extends Grass {


    public Clover(int x, int y) {
        super(1, 200, x, y);
    }

    @Override
    public String toString() {
        return Constants.CLOVER;
    }
}
