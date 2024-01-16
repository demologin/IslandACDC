package com.javarush.island.boyarinov;

import com.javarush.island.boyarinov.entities.map.Cell;
import com.javarush.island.boyarinov.entities.map.Island;
import com.javarush.island.boyarinov.entities.organism.animals.Animal;
import com.javarush.island.boyarinov.entities.organism.animals.herbivores.Sheep;
import com.javarush.island.boyarinov.entities.organism.animals.predators.Wolf;

public class Main {

    public static void main(String[] args) {

        Island island = new Island(3, 3);
        Cell[][] cells = island.getMap();
        Cell cell = cells[1][2];
        Animal someAnimal = new Sheep(island);
        Animal someAnimalTwo = new Sheep(island);
        Animal someAnimalThree = new Wolf(island);
        someAnimal.setLocation(cell);
        cell.getOrganismsSet().add(someAnimal);
        cell.getOrganismsSet().add(someAnimalTwo);
        cell.getOrganismsSet().add(someAnimalThree);
        someAnimal.move();

    }
}
