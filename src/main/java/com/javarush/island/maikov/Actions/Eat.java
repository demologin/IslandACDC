package com.javarush.island.maikov.Actions;

import com.javarush.island.maikov.Abstraction.Organism;
import com.javarush.island.maikov.Abstraction.Herbivore;
import com.javarush.island.maikov.Abstraction.Predator;
import com.javarush.island.maikov.Abstraction.Grass;
import com.javarush.island.maikov.MapOfIsland.MapOfIsland;
import com.javarush.island.maikov.RandomEating.RandomEating;
import com.javarush.island.maikov.methods.Statistics;

import java.util.HashSet;

public class Eat {
    private final Statistics statistics = new Statistics();
    private final RandomEating randomEating = new RandomEating();
    public void startEat(Organism someOrganism) {
        synchronized (MapOfIsland.mapOfIsland) {
            if (someOrganism instanceof Herbivore) {
                int x = ((Herbivore) someOrganism).getX();
                int y = ((Herbivore) someOrganism).getY();
                // new HashSet to avoid ConcurrentException
                HashSet<Organism> oneSpaceOfIsland = new HashSet<>(MapOfIsland.mapOfIsland[x][y]);
                for (Organism eatenGrass : oneSpaceOfIsland) {
                    if (eatenGrass instanceof Grass) {
                        // all herbivores eat grass 100%, if we have here only herbivores, no point in checking.
                        ((Grass) eatenGrass).getThread().interrupt();
                        MapOfIsland.mapOfIsland[x][y].remove(eatenGrass);
                        statistics.removeFromStatistics(eatenGrass);
                        statistics.countEating();
                        setLife(someOrganism, eatenGrass);
                        return;
                    }
                }
            }
            if (someOrganism instanceof Predator) {
                int x = ((Predator) someOrganism).getX();
                int y = ((Predator) someOrganism).getY();
                // new HashSet to avoid ConcurrentException
                HashSet<Organism> oneSpaceOfIsland = new HashSet<>(MapOfIsland.mapOfIsland[x][y]);
                for (Organism eatenAnimal : oneSpaceOfIsland) {
                    if (eatenAnimal instanceof Herbivore) {
                        if(!randomEating.getRandomEating(someOrganism, eatenAnimal)){
                            statistics.countEating();
                            return;
                        }
                        ((Herbivore) eatenAnimal).getThread().interrupt();
                        MapOfIsland.mapOfIsland[x][y].remove(eatenAnimal);
                        statistics.removeFromStatistics(eatenAnimal);
                        statistics.countEating();
                        setLife(someOrganism, eatenAnimal);
                        return;
                    }
                }
            }
        }
    }
// An animal regains its life when it eats. Life can't be more maxFood
    private void setLife(Organism someOrganism, Organism eatenOrganism) {
        if(someOrganism instanceof Predator) {
            double maxFood = ((Predator) someOrganism).getMaxFood();
            double weightEatenOrganism = ((Herbivore) eatenOrganism).getWeight();
            double lifeSomeAnimal = ((Predator) someOrganism).getLife();
            double newLife = Math.min((weightEatenOrganism + lifeSomeAnimal), maxFood);
            ((Predator) someOrganism).setLife(newLife);
        }
        if (someOrganism instanceof Herbivore){
            double maxFood = ((Herbivore) someOrganism).getMaxFood();
            double weightEatenOrganism = ((Grass) eatenOrganism).getWeight();
            double lifeSomeAnimal = ((Herbivore) someOrganism).getLife();
            double newLife = Math.min((weightEatenOrganism + lifeSomeAnimal), maxFood);
            ((Herbivore) someOrganism).setLife(newLife);
        }
    }
}

