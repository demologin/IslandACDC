package com.javarush.island.boyarinov.util;

import java.util.concurrent.ThreadLocalRandom;

public class Random {

    public static int getRndNumber(int origin, int bound) {
        return ThreadLocalRandom.current().nextInt(origin, bound);
    }
}
