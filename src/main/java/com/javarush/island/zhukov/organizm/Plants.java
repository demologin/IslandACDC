package com.javarush.island.zhukov.organizm;


import com.javarush.island.zhukov.constans.TextConstants;

public class Plants extends Organism{
    public Plants(int x, int  y) {
        super(15,1, x, y, 200);
    }

    @Override
    public String toString() {
        return TextConstants.PLANTS;
    }
}
