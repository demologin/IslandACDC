package com.javarush.island.boyarinov.util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomNum {

    public static double getRndNumber(double origin, double bound) {
        return ThreadLocalRandom.current().nextDouble(origin, bound);
    }
    public static int getRndNumber(int origin, int bound) {
        return ThreadLocalRandom.current().nextInt(origin, bound);
    }

    public static boolean get(int percentProbability) {
        return ThreadLocalRandom.current().nextInt(0, 100) < percentProbability;
    }
}
