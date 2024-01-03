package com.javarush.island.maikov.Actions;
import com.javarush.island.maikov.Abstraction.Organism;
import com.javarush.island.maikov.Animals.Herbivore.Herbivore;
import com.javarush.island.maikov.Animals.Herbivore.Rabbit;
import com.javarush.island.maikov.Animals.Predators.Predator;
import com.javarush.island.maikov.Animals.Predators.Wolf;
import com.javarush.island.maikov.MapOfIsland;

import java.util.ArrayList;
// of course, I can use Reflection, but when I started making, it looks more deal for JVM, so I decided share to methods,
// a lot of codes, but this code easier for JVM.
public class Reproduce {
    public static void reproduce(Organism someOrganism) throws InterruptedException {
        synchronized (MapOfIsland.mapOfIsland) {
            if (someOrganism instanceof Herbivore) {
                int x = ((Herbivore) someOrganism).getX();
                int y = ((Herbivore) someOrganism).getY();
                ArrayList<Organism> oneSpaceOfIsland = MapOfIsland.mapOfIsland[x][y];
                for (Organism organism : oneSpaceOfIsland) {
                    if (organism.getClass().equals(someOrganism.getClass())) {
                        Rabbit newRabbit = new Rabbit(x, y);
                        MapOfIsland.mapOfIsland[x][y].add(newRabbit);
                        Thread.sleep(1000);
                        return;
                    }
                }
            }
            if (someOrganism instanceof Predator) {
                int x = ((Predator) someOrganism).getX();
                int y = ((Predator) someOrganism).getY();
                ArrayList<Organism> oneSpaceOfIsland = MapOfIsland.mapOfIsland[x][y];
                for (Organism organism : oneSpaceOfIsland) {
                    if (organism.getClass().equals(someOrganism.getClass())) {
                        Wolf newWolf = new Wolf(x, y);
                        MapOfIsland.mapOfIsland[x][y].add(newWolf);
                        Thread.sleep(1000);
                        return;
                    }
                }
            }
        }
    }
}

