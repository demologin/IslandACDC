package com.javarush.island.zhukov.organizm.herbivores;

import com.javarush.island.zhukov.constans.TextConstants;

public class Goat extends Herbivores{
    public Goat(int x, int y) {
        super(60, 3, 140, 10, x, y,9);
    }
    @Override
    public String toString() {
        return TextConstants.GOAT;
    }
}
