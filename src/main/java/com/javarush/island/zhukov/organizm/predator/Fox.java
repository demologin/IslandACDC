package com.javarush.island.zhukov.organizm.predator;

import com.javarush.island.zhukov.constans.TextConstants;

public class Fox extends Predator{
    public Fox(int x, int y) {
        super(8, 2, 30, 2, x, y,2);
    }
    @Override
    public String toString() {
        return TextConstants.FOX;
    }
}
