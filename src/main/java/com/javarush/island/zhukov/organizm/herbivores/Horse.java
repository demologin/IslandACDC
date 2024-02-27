package com.javarush.island.zhukov.organizm.herbivores;

import com.javarush.island.zhukov.constans.TextConstants;

public class Horse extends Herbivores{
    public Horse(int x, int y) {
        super(400, 4, 20, 60, x, y,5);
    }
    @Override
    public String toString() {
        return TextConstants.HORSE;
    }
}
