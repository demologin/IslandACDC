package com.javarush.island.boyarinov.entities.map;


import com.javarush.island.boyarinov.constants.Constants;
import com.javarush.island.boyarinov.constants.Limit;
import com.javarush.island.boyarinov.entities.organism.Organisms;
import com.javarush.island.boyarinov.util.RandomNum;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Island {

    private final int row;
    private final int column;
    private Cell[][] map;

    public Island(int row, int column) {
        this.row = row;
        this.column = column;
        initializeMap();
    }

    private void initializeMap() {
        map = new Cell[this.row][this.column];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Cell();
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j].initAvailableCells(this, i, j);
            }
        }
    }

    public void fillIsland(Map<Class<? extends Organisms>, Organisms> prototype) {
        for (Cell[] cells : map) {
            for (Cell cell : cells) {
                if (!RandomNum.get(Constants.CHANCE_FILLING)) {
                    continue;
                }
                Set<Organisms> organismsSet = cell.getOrganismsSet();
                Organisms organism = getRndOrganisms(prototype);
                int numberAnimalToCell = getNumberAnimalToCell(organismsSet, organism);
                for (int i = 0; i < numberAnimalToCell; i++) {
                    Organisms clone = organism.clone();
                    organismsSet.add(clone);
                }
            }
        }
    }

    private int getNumberAnimalToCell(Set<Organisms> organismsSet, Organisms organism) {
        int maxAnimalsToCell = Limit.getMaxOfAnimalsToCell().get(organism.getName());
        int numberAnimalToCell = RandomNum.getRndNumber(1, maxAnimalsToCell);
        int numberAnimalToCurrentCell = countNumberAnimal(organismsSet, organism);
        int diff = maxAnimalsToCell - numberAnimalToCurrentCell;
        return Math.min(numberAnimalToCell, diff);
    }

    private int countNumberAnimal(Set<Organisms> organismsSet, Organisms organism) {
        Set<Organisms> thisOrganismSet = organismsSet.stream()
                .filter(o -> o.getClass().equals(organism.getClass()))
                .collect(Collectors.toSet());
        return thisOrganismSet.size();
    }

    private Organisms getRndOrganisms(Map<Class<? extends Organisms>, Organisms> prototype) {
        int numberOrganisms = Constants.ANIMAL_CLASS_NAME.length;
        int rndIndex = RandomNum.getRndNumber(0, numberOrganisms);
        Class<? extends Organisms> animalClassName = Constants.ANIMAL_CLASS_NAME[rndIndex];
        return prototype.get(animalClassName);
    }

    public Cell[][] getMap() {
        return map;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
