package com.javarush.island.khasanov.view;

import com.javarush.island.khasanov.entity.Island;

public class ConsoleView implements View {
    private final Island island;

    public ConsoleView(Island island) {
        this.island = island;
    }

    public void show() {
        System.out.println("-".repeat(60));
        island.getIslandMap().forEach((key, value) -> System.out.println(key + " " + value));
        System.out.println("-".repeat(60));
    }
}
