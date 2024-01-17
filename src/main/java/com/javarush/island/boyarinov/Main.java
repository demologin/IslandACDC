package com.javarush.island.boyarinov;

import com.javarush.island.boyarinov.entities.map.Cell;
import com.javarush.island.boyarinov.entities.map.Island;
import com.javarush.island.boyarinov.entities.organism.animals.Animal;
import com.javarush.island.boyarinov.entities.organism.animals.herbivores.Sheep;

public class Main {

    public static void main(String[] args) {

        Island island = new Island(3, 3);
        Cell[][] map = island.getMap();
        Animal sheep = new Sheep();
        sheep.setMaxOfAnimalsToCell(5);
        sheep.setTravelSpeed(3);
        Cell cell = map[1][2];
        cell.getOrganismsSet().add(sheep);
        sheep.move(cell);

    }
}
