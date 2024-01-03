package com.javarush.island.maikov.Animals.Herbivore;

import com.javarush.island.maikov.Constants;
import com.javarush.island.maikov.Enum.ListOfHerbivore;

public class Rabbit extends Herbivore {


    public Rabbit(int x, int y) {
        super(2,150,2,0.45, ListOfHerbivore.RABBIT, x, y);
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public String toString() {
        return Constants.RABBIT;
    }
}
