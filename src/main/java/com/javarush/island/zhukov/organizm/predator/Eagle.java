package com.javarush.island.zhukov.organizm.predator;

import com.javarush.island.zhukov.constans.TextConstants;

public class Eagle extends Predator{
    public Eagle(int x, int y) {
        super(6, 3, 20, 1, x, y,4);
    }
    @Override
    public String toString() {
        return TextConstants.EAGLE;
    }
}
