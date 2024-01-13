package com.javarush.island.maikov.Animals.Herbivore;

import com.javarush.island.maikov.Constants;

public class Rabbit extends Herbivore {

    public Rabbit(int x, int y) {super(2,150,2,0.45, x, y);
    }


    @Override
    public String toString() {
        return Constants.RABBIT;
    }
}
