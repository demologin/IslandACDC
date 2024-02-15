package com.javarush.island.zhukov.organizm.herbivores;

import com.javarush.island.zhukov.constans.TextConstants;

public class Caterpillar extends Herbivores{
    public Caterpillar(int x, int y) {
        super(0.01, 0, 1000, 0, x, y,14);
    }

    @Override
    public String toString() {
        return TextConstants.CATERPILLARS;
    }
}
