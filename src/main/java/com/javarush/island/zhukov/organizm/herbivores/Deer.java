package com.javarush.island.zhukov.organizm.herbivores;

import com.javarush.island.zhukov.constans.TextConstants;

public class Deer extends Herbivores{
    public Deer(int x, int y) {
        super(300, 4, 20, 50, x, y,6);
    }

    @Override
    public String toString() {
        return TextConstants.DEER;
    }
}
