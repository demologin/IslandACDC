package com.javarush.island.zhukov.organizm.predator;

import com.javarush.island.zhukov.constans.TextConstants;

public class Snake extends Predator{
    public Snake(int x, int y) {
        super(15, 1, 30, 3, x, y,1);
    }
    @Override
    public String toString() {
        return TextConstants.SNAKE;
    }
}
