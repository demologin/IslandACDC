package com.javarush.island.zhukov.organizm.herbivores;

import com.javarush.island.zhukov.constans.TextConstants;

public class Mouse extends Herbivores{
    public Mouse(int x, int y) {
        super(0.05, 1, 500, 0.01, x, y,8);
    }
    @Override
    public String toString() {
        return TextConstants.MOUSE;
    }
}
