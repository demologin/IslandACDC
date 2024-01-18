package com.javarush.island.kotovych.util;

import java.util.concurrent.ThreadLocalRandom;

public class Rnd {
    private Rnd(){}

    public static int nextInt(int origin, int bound){
       return ThreadLocalRandom.current().nextInt(origin, bound);
    }

    public static double nextDouble(double origin, double bound){
        return ThreadLocalRandom.current().nextDouble(origin, bound);
    }

    public static int nextInt(int bound){
        return ThreadLocalRandom.current().nextInt( bound);
    }

    public static double nextDouble(double bound){
        return ThreadLocalRandom.current().nextDouble(bound);
    }
}
