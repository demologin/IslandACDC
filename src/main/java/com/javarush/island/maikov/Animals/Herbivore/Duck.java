package com.javarush.island.maikov.Animals.Herbivore;


import com.javarush.island.maikov.Abstraction.Herbivore;
import com.javarush.island.maikov.Constants;

public class Duck extends Herbivore {
    public Duck(int x, int y) {
        super(1,200,4,0.15, x, y);
    }

    @Override
    public String toString() {
        return Constants.DUCK;
    }
}
