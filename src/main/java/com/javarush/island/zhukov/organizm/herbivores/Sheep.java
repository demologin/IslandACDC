package com.javarush.island.zhukov.organizm.herbivores;

import com.javarush.island.zhukov.constans.TextConstants;

public class Sheep extends Herbivores{
    public Sheep(int x, int y) {
        super(70, 3, 140, 15, x, y,10);
    }
    @Override
    public String toString() {
        return TextConstants.SHEEP;
    }
}
