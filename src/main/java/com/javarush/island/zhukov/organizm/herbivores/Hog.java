package com.javarush.island.zhukov.organizm.herbivores;

import com.javarush.island.zhukov.constans.TextConstants;

public class Hog extends Herbivores{
    public Hog(int x, int y) {
        super(400, 2, 50, 50, x, y,11);
    }
    @Override
    public String toString() {
        return TextConstants.HOG;
    }
}
