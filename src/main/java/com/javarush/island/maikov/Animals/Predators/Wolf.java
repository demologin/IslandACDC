package com.javarush.island.maikov.Animals.Predators;

import com.javarush.island.maikov.Constants;
import com.javarush.island.maikov.Enum.ListOfPredators;

public class Wolf extends Predator {

    public Wolf(int x, int y) {
        super(50, 30, 3, 8, ListOfPredators.WOLF, x, y);
    }
    @Override
    public String toString() {
        return Constants.WOLF;
    }
}
