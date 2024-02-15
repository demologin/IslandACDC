package com.javarush.island.zhukov.organizm.predator;

import com.javarush.island.zhukov.constans.TextConstants;

public class Wolf extends Predator{
    public Wolf(int x, int y) {
        super(50, 3, 30, 8, x, y, 0);
    }
    @Override
    public String toString() {
        return TextConstants.WOLF;
    }
}
