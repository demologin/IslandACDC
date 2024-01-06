package com.javarush.island.kotovych.settings;

import lombok.Getter;
import lombok.Setter;

public class Settings {
    @Getter
    @Setter
    private static long delay;
    @Getter
    @Setter
    private static int maxAnimalsOnOneSquare = 2000;
    @Getter
    @Setter
    private static int animalsOnSquareAtTheBeginning = 1000;
    @Getter
    private static long defaultDelay = 2000;

    private Settings(){}
}
