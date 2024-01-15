package com.javarush.island.maikov.Animals.Herbivore;

import com.javarush.island.maikov.Abstraction.Herbivore;
import com.javarush.island.maikov.Constants;

public class Mouse extends Herbivore {
    public Mouse(int x, int y) {
        super(0.05, 500, 1, 0.01, x, y);
    }

    @Override
    public String toString() {
        return Constants.MOUSE;
    }
}
