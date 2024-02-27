package com.javarush.island.zhukov.organizm.herbivores;

import com.javarush.island.zhukov.constans.TextConstants;

public class Rabbit extends Herbivores{
    public Rabbit(int x, int y) {
        super(2, 2, 150, 0.45, x, y,7);
    }
    @Override
    public String toString() {
        return TextConstants.RABBIT;
    }
}
