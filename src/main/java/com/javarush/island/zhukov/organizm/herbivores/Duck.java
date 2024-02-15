package com.javarush.island.zhukov.organizm.herbivores;

import com.javarush.island.zhukov.constans.TextConstants;

public class Duck extends Herbivores{
    public Duck(int x, int y) {
        super(1, 4, 200, 0.15, x, y,13);
    }

    @Override
    public String toString() {
        return TextConstants.DUCK;
    }
}
