package com.javarush.island.kotovych.organisms;

import com.javarush.island.kotovych.exceptions.AppException;
import com.javarush.island.kotovych.factory.OrganismFactory;
import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.game.Square;
import com.javarush.island.kotovych.settings.Settings;
import com.javarush.island.kotovych.util.*;
import javafx.application.Platform;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
@Setter
public class Flock {
    private List<Organism> organisms = new CopyOnWriteArrayList<>();
    private String name;
    private Semaphore semaphore = new Semaphore(1);

    private boolean blocked = false;

    private int maxOnOneSquare;
    private int maxStepSize;

    public Flock(String name, int animals) {
        this.name = name;
        Map<String, Double> data = OrganismDataTable.getData(this.getName());
        setMaxOnOneSquare(data.get("maxOnOneSquare").intValue());
        setMaxStepSize(data.get("maxStepSize").intValue());
        for (int i = 0; i < animals; i++) {
            organisms.add(OrganismFactory.newOrganism(name));
        }
    }

    public void removeOrganism(Organism organism, Square square) {
        try {
            blockOtherThreadsInFlock();
            organisms.remove(organism);
            square.removeOrganismFromMap(organism);
        } catch (Exception e) {
            throw new AppException(e);
        } finally {
            unblockOtherThreadsInFlock();
        }
    }

    public void addOrganism(Organism organism, Square square) {
        try {
            blockOtherThreadsInFlock();
            if (organisms.size() < Settings.getMaxFlockSize()) {
                organisms.add(organism);
                square.addOrganismToMap(organism);
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
            int stepSize = Rnd.nextInt(maxStepSize + 1);

            int squareX = currentSquare.getX();
            int squareY = currentSquare.getY();

            try {
                Direction direction = Direction.values()[Rnd.nextInt(Direction.values().length)];
                int newX = squareX;
                int newY = squareY;

                switch (direction) {
                    case UP -> newY += stepSize;
                    case RIGHT -> newX += stepSize;
                    case DOWN -> newY -= stepSize;
                    case LEFT -> newX -= stepSize;
                }

                Square neededSquare = gameScene.getSquareByCoordinates(newX, newY);
                if(neededSquare.addFlock(this)) {
                    currentSquare.removeFlock(this);
                }

            } catch (ArrayIndexOutOfBoundsException e) {
            }
        } catch (Exception e) {
            Platform.runLater(() -> ShowAlert.showErrorWithStacktrace(e.getMessage(), e));
        } finally {
            unblockOtherThreadsInFlock();
        }
    }

    public void eat(Square currentSquare) {
        try {
            blockOtherThreadsInFlock();
            AtomicBoolean flockAte = new AtomicBoolean(false);
            currentSquare.getFlockList()
                    .stream()
                    .filter(flock -> {
                        int probability = ProbabilityTable.getProbability(this.getName(), flock.getName());
                        if (probability == 0) {
                            return false;
                        }
                        int number = Rnd.nextInt(probability);
                        return number <= probability;
                    })
                    .filter(flock -> !flock.isBlocked())
                    .forEach(flock -> {
                        if (!flockAte.get()) {
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
                                    organisms.get(i).setAte(true);
                                }
                                if (!organisms.get(i).isAte()) {
                                    organisms.get(i).setWeight(organisms.get(i).getWeight() * 0.9);
                                }
                                organisms.get(i).setAte(false);
                            }
                            flockAte.set(true);
                        }
                    });
        } catch (Exception e) {
            Platform.runLater(() -> ShowAlert.showErrorWithStacktrace(e.getMessage(), e));
        } finally {
            unblockOtherThreadsInFlock();
        }
    }

    private void blockOtherThreadsInFlock() {
        try {
            semaphore.acquire();
            blocked = true;
        } catch (InterruptedException e) {
            Platform.runLater(() -> ShowAlert.showErrorWithStacktrace(e.getMessage(), e));
        }
    }

    public void unblockOtherThreadsInFlock() {
        if (semaphore.availablePermits() == 0) {
            semaphore.release();
            blocked = false;
        }
    }
}
