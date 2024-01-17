package com.javarush.island.khasanov.util;

import com.javarush.island.khasanov.config.Setting;
import com.javarush.island.khasanov.repository.Position;

import java.util.concurrent.ThreadLocalRandom;

public interface Rndm {
     static Position nextPosition() {
        int x = ThreadLocalRandom.current().nextInt(Setting.width);
        int y = ThreadLocalRandom.current().nextInt(Setting.height);

        return new Position(x, y);
    }

    static int nextSteps(int maxSteps, int limit) {
         int steps = ThreadLocalRandom.current().nextInt(maxSteps+1);

         if (steps > limit) {
             steps = limit;
         }

         return steps;
    }

    static int choose(int a, int b) {
         if (ThreadLocalRandom.current().nextBoolean()) {
             return a;
         }
         else {
             return b;
         }
    }

}
