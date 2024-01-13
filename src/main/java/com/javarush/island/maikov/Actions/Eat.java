package com.javarush.island.maikov.Actions;

import com.javarush.island.maikov.Abstraction.Organism;
import com.javarush.island.maikov.Animals.Herbivore.Herbivore;
import com.javarush.island.maikov.Animals.Predators.Predator;
import com.javarush.island.maikov.Grass.AbstractionGrass;
import com.javarush.island.maikov.MapOfIsland;
import com.javarush.island.maikov.methods.Statistics;

import java.util.ArrayList;

public class Eat {
    public static void eat(Organism someOrganism) {
        Statistics statistics = new Statistics();
        synchronized (MapOfIsland.mapOfIsland) {
            if (someOrganism instanceof Herbivore) {
                int x = ((Herbivore) someOrganism).getX();
                int y = ((Herbivore) someOrganism).getY();
                ArrayList<Organism> oneSpaceOfIsland = new ArrayList<>(MapOfIsland.mapOfIsland[x][y]);
                for (Organism organism : oneSpaceOfIsland) {
                    if (organism instanceof AbstractionGrass) {
                        ((AbstractionGrass) organism).getThread().interrupt();
                        MapOfIsland.mapOfIsland[x][y].remove(organism);
                        statistics.removeFromStatistics(organism);
                        return;
                    }
                }
            }
            if (someOrganism instanceof Predator) {
                int x = ((Predator) someOrganism).getX();
                int y = ((Predator) someOrganism).getY();
                ArrayList<Organism> oneSpaceOfIsland = new ArrayList<>(MapOfIsland.mapOfIsland[x][y]);
                for (Organism organism : oneSpaceOfIsland) {
                    if (organism instanceof Herbivore) {
                        ((Herbivore) organism).getThread().interrupt();
                        MapOfIsland.mapOfIsland[x][y].remove(organism);
                        statistics.removeFromStatistics(organism);
                        return;
                    }
                }
            }
        }
    }
}

