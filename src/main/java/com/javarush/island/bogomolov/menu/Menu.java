package com.javarush.island.bogomolov.menu;

import com.javarush.island.bogomolov.storage.IslandInitialization;
import com.javarush.island.bogomolov.storage.IslandMap;

public class Menu {
    private final StartParameters startParameters;

    public Menu() {
        startParameters = new StartParameters();
    }

    public void startGame() {
        System.out.println(Messages.CHANGE_DEFAULT_PARAMETERS);
        System.out.println(Messages.YES);
        System.out.println(Messages.NO);
        int getOption = startParameters.getOption(1, 2);
        if (getOption == 1) {
            startParameters.userStartParameters();
        } else {
            IslandMap.getislandMap().createDefaultMap();
            IslandInitialization.getIslandInitialization().createIsland();
        }
        System.out.println(Messages.DOWNLOAD_PROCESS);
    }
}
