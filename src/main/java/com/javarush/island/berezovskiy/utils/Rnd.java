package com.javarush.island.berezovskiy.utils;

import java.util.concurrent.ThreadLocalRandom;

public final class Rnd {
    private Rnd() {
    }

    public static int getRandom() {
        return ThreadLocalRandom.current().nextInt();
    }

    public static int getRandom(int value) {
        return ThreadLocalRandom.current().nextInt(value);
    }

    public static int getRandom(int value1, int value2) {
        return ThreadLocalRandom.current().nextInt(value1, value2);
    }

    public static boolean getRandomBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }
}
