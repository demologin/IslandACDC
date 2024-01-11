package com.javarush.island.maikov.Grass;


import com.javarush.island.maikov.Constants;

public class Clover extends AbstractionGrass {


    public Clover(int x, int y) {
        super(1, 200, x, y);
    }

    @Override
    public String toString() {
        return Constants.CLOVER;
    }
}
