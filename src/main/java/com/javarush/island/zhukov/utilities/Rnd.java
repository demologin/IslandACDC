package com.javarush.island.zhukov.utilities;

import java.util.concurrent.ThreadLocalRandom;

public class Rnd {
    public static int randomNumber(int startNumber, int finishNumber){
        return ThreadLocalRandom.current().nextInt(startNumber, finishNumber);
    }
    public static boolean randomBoolean(){
        int randomNumber = ThreadLocalRandom.current().nextInt(0, 2);
        return randomNumber==0;
    }
    public static boolean probabilityCalc(int probability){
        int randomNumber = randomNumber(1,101);
        return probability <= randomNumber;
    }

}
