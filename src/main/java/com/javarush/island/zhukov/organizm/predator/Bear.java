package com.javarush.island.zhukov.organizm.predator;

import com.javarush.island.zhukov.constans.TextConstants;

public class Bear extends Predator{
    public Bear(int x, int y) {
        super(500, 2, 5, 80, x, y,3);
    }
    @Override
    public String toString() {
        return TextConstants.BEAR;
    }
}
