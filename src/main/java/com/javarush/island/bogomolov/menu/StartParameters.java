package com.javarush.island.bogomolov.menu;

import com.javarush.island.bogomolov.storage.IslandInitialization;
import com.javarush.island.bogomolov.storage.IslandMap;

import java.util.Scanner;

public class StartParameters {
    public void userStartParameters() {
        changeIslandSize();
        int numberOfPlants = changeNumberOfPlants();
        int numberOfHerbivores = changeNumberOfHerbivores();
        int numberOfPredators = changeNumberOfPredators();
        if (numberOfPlants == 0) {
            numberOfPlants = IslandInitialization.getIslandInitialization().getNumberOfPlants();
        }
        if (numberOfHerbivores == 0) {
            numberOfHerbivores = IslandInitialization.getIslandInitialization().getNumberOfHerbivores();
        }
        if (numberOfPredators == 0) {
            numberOfPredators = IslandInitialization.getIslandInitialization().getNumberOfPredators();
        }
        IslandInitialization.getIslandInitialization().createIsland(numberOfHerbivores, numberOfPredators, numberOfPlants);

    }

    private void changeIslandSize() {
        System.out.println(Messages.CHANGE_ISLAND_SIZE + ":" + IslandMap.getislandMap().getRows() + "X" + IslandMap.getislandMap().getColumns());
        System.out.println(Messages.YES);
        System.out.println(Messages.NO);
        int getOption = getOption(1, 2);
        if (getOption == 1) {
            System.out.println(Messages.NUMBER_OF_ROWS);
            int rows = getOption(1, 500);
            System.out.println(Messages.NUMBER_OF_COLUMNS);
            int columns = getOption(1, 500);
            IslandMap.getislandMap().createMap(rows, columns);
        } else {
            IslandMap.getislandMap().createDefaultMap();
        }

    }

    private int changeNumberOfHerbivores() {
        System.out.println(Messages.CHANGE_NUMBER_OF_HERBIVORES + ":" + IslandInitialization.getIslandInitialization().getNumberOfHerbivores());
        System.out.println(Messages.YES);
        System.out.println(Messages.NO);
        int numberOfHerbivores = 0;
        int getOption = getOption(1, 2);
        if (getOption == 1) {
            System.out.println(Messages.NUMBER_OF_HERBIVORES);
            numberOfHerbivores = getOption(10, 500);
        }
        return numberOfHerbivores;
    }

    private int changeNumberOfPredators() {
        System.out.println(Messages.CHANGE_NUMBER_OF_PREDATORS + ":" + IslandInitialization.getIslandInitialization().getNumberOfPredators());
        System.out.println(Messages.YES);
        System.out.println(Messages.NO);
        int numberOfPredators = 0;
        int getOption = getOption(1, 2);
        if (getOption == 1) {
            System.out.println(Messages.NUMBER_OF_PREDATORS);
            numberOfPredators = getOption(10, 500);
        }
        return numberOfPredators;

    }

    private int changeNumberOfPlants() {
        System.out.println(Messages.CHANGE_NUMBER_OF_PLANTS + ":" + IslandInitialization.getIslandInitialization().getNumberOfPlants());
        System.out.println(Messages.YES);
        System.out.println(Messages.NO);
        int numberOfPlants = 0;
        int getOption = getOption(1, 2);
        if (getOption == 1) {
            System.out.println(Messages.NUMBER_OF_PLANTS);
            numberOfPlants = getOption(1, 400);
        }
        return numberOfPlants;
    }

    public int getOption(int minQuantity, int maxQuantity) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextInt()) {
                int getOption = scanner.nextInt();
                if (getOption >= minQuantity && getOption <= maxQuantity) {
                    return getOption;
                } else {
                    System.out.println(Messages.NUMBER_IS_OUT_OF_BOUNDS);
                }
            } else {
                scanner.next();
                System.out.println(Messages.NOT_A_NUMBER);
            }
        }


    }
}
