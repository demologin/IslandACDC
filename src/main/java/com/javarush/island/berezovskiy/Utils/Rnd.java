package com.javarush.island.berezovskiy.Utils;

import java.util.concurrent.ThreadLocalRandom;

public final class Rnd {


    public static int getRandom(){
        return ThreadLocalRandom.current().nextInt();
    }
    public static int getRandom(int value){
        return ThreadLocalRandom.current().nextInt(value);
    }
    public static int getRandom(int value1, int value2){
        return ThreadLocalRandom.current().nextInt(value1, value2);
    }
}
