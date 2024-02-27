package com.javarush.island.zhukov.organizm.herbivores;

import com.javarush.island.zhukov.constans.TextConstants;

public class Bull extends Herbivores{
    public Bull(int x, int y) {
        super(700, 3, 10, 100, x, y,12);
    }

    @Override
    public String toString() {
        return TextConstants.BULL;
    }
}
