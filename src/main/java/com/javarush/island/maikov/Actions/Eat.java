package com.javarush.island.maikov.Actions;

import com.javarush.island.maikov.Abstraction.Organism;
import com.javarush.island.maikov.Animals.Herbivore.Herbivore;
import com.javarush.island.maikov.Animals.Herbivore.Rabbit;
import com.javarush.island.maikov.Animals.Predators.Predator;
import com.javarush.island.maikov.Constants;
import com.javarush.island.maikov.MapOfIsland;

import java.util.ArrayList;
import java.util.Map;

public class Eat {
    public static void eat(Organism someOrganism) throws InterruptedException {
        synchronized (MapOfIsland.mapOfIsland){
//            if (someOrganism instanceof Herbivore) {
//                int x = ((Herbivore) someOrganism).getX();
//                int y = ((Herbivore) someOrganism).getY();
//                ArrayList<Organism> oneSpaceOfIsland = MapOfIsland.mapOfIsland[x][y];
//                for (Organism organism : oneSpaceOfIsland) {
//                    if (organism.getClass().equals(someOrganism.getClass())) {
//                        Rabbit newRabbit = new Rabbit(x, y);
//                        MapOfIsland.mapOfIsland[x][y].add(newRabbit);
//                        Thread.sleep(1000);
//                        return;
//                    }
//                }
//            }
            if (someOrganism instanceof Predator) {
                int x = ((Predator) someOrganism).getX();
                int y = ((Predator) someOrganism).getY();
                ArrayList<Organism> oneSpaceOfIsland = MapOfIsland.mapOfIsland[x][y];
                for (Organism organism : oneSpaceOfIsland) {
                    if (organism instanceof Herbivore) {
                        ((Herbivore) organism).getThread().interrupt();
                        MapOfIsland.mapOfIsland[x][y].remove(organism);
                        return;
                    }
                }
            }
        }
    }
}
