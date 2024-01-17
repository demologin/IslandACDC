package com.javarush.island.kotovych.organisms;

import com.javarush.island.kotovych.exceptions.AppException;
import com.javarush.island.kotovych.factory.OrganismFactory;
import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.game.Square;
import com.javarush.island.kotovych.settings.Settings;
import com.javarush.island.kotovych.util.Direction;
import com.javarush.island.kotovych.util.OrganismDataTable;
import com.javarush.island.kotovych.util.ProbabilityTable;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
@Setter
public class Flock {
    private List<Organism> organisms = new CopyOnWriteArrayList<>();
    private String name;
    private Semaphore semaphore = new Semaphore(1);

    public Flock(String name, int animals) {
        this.name = name;
        for (int i = 0; i < animals; i++) {
            organisms.add(OrganismFactory.newOrganism(name));
        }
    }

    public void removeOrganism(Organism organism, Square square) {
        try {
            blockOtherThreadsInFlock();
            organisms.remove(organism);
            square.getTotalAnimalsInSquare().decrementAndGet();
            square.getStatistics().getTotalOrganisms().decrementAndGet();
            square.getStatistics().getTotalOrganismCount().get(organism.getName()).decrementAndGet();
        } catch (Exception e) {
            throw new AppException(e);
        } finally {
            unblockOtherThreadsInFlock();
        }
    }

    public void addOrganism(Organism organism, Square square) {
        try {
            blockOtherThreadsInFlock();
            if (organisms.size() + 1 < Settings.getMaxFlockSize()) {
                organisms.add(organism);
                square.addOrganismToMap(organism);
                square.getTotalAnimalsInSquare().incrementAndGet();
                square.getStatistics().getTotalOrganismCount().get(organism.getName()).incrementAndGet();
                square.getStatistics().getTotalOrganisms().incrementAndGet();
            }
        } catch (Exception e) {
            throw new AppException(e);
        } finally {
            unblockOtherThreadsInFlock();
        }
    }

    public void move(Square currentSquare, GameScene gameScene) {
        try {
            blockOtherThreadsInFlock();
            if (gameScene.getSquares().size() <= 1) {
                return;
            }
            int maxStepSize = OrganismDataTable.getData(this.getName()).get("maxStepSize").intValue();
            int stepSize = ThreadLocalRandom.current().nextInt(maxStepSize + 1);

            int squareX = currentSquare.getX();
            int squareY = currentSquare.getY();

            try {
                Direction direction = Direction.values()[ThreadLocalRandom.current().nextInt(Direction.values().length)];
                int newX = squareX;
                int newY = squareY;

                switch (direction) {
                    case UP -> newY += stepSize;
                    case RIGHT -> newX += stepSize;
                    case DOWN -> newY -= stepSize;
                    case LEFT -> newX -= stepSize;
                }

                Square neededSquare = gameScene.getSquareByCoordinates(newX, newY);
                if (neededSquare.addFlock(this)) {
                    currentSquare.removeFlock(this);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        } catch (Exception e) {
            throw new AppException(e);
        } finally {
            unblockOtherThreadsInFlock();
        }
    }

    public void eat(Square currentSquare) {
        try {
            AtomicBoolean ate = new AtomicBoolean(false);
            currentSquare.getFlockList()
                    .stream()
                    .filter(flock -> {
                        int probability = ProbabilityTable.getProbability(this.getName(), flock.getName());
                        int number = ThreadLocalRandom.current().nextInt(probability);
                        return number <= probability;
                    })
                    .forEach(flock -> {
                        if (!ate.get()) {
                            double maxWeight = OrganismDataTable.getData(name).get("weight");
                            List<Organism> otherFlockOrganisms = flock.getOrganisms();
                            for (int i = 0; i < organisms.size(); i++) {
                                if (organisms.get(i).getWeight() > maxWeight * 0.4) {
                                    continue;
                                }
                                organisms.get(i).addWeight(otherFlockOrganisms.get(i).getWeight());
                                if (organisms.get(i).getWeight() > maxWeight) {
                                    organisms.get(i).setWeight(maxWeight);
                                    otherFlockOrganisms.get(i).die(flock, currentSquare);
                                }
                            }
                            ate.set(true);
                        }
                    });
        } catch (Exception e){
            throw new AppException(e);
        } finally {
            unblockOtherThreadsInFlock();
        }
    }

    private void blockOtherThreadsInFlock() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new AppException(e);
        }
    }

    public void unblockOtherThreadsInFlock() {
        if (semaphore.availablePermits() == 0) {
            semaphore.release();
        }
    }
}
