package com.javarush.island.kotovych.organisms.animals;

import com.javarush.island.kotovych.factory.OrganismFactory;
import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.organisms.Organism;
import com.javarush.island.kotovych.game.Square;
import com.javarush.island.kotovych.util.Direction;
import com.javarush.island.kotovych.util.OrganismDataTable;
import com.javarush.island.kotovych.util.ProbabilityTable;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public abstract class Animal extends Organism {
    public void move(Square currentSquare, GameScene gameScene) {
        try {
            blockOtherThreads();
            if (gameScene.getSquares().size() <= 1) {
                return;
            }
            int stepSize = ThreadLocalRandom.current().nextInt(0, this.getMaxStepSize() + 1);

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
                if (neededSquare.addOrganism(this)) {
                    currentSquare.removeOrganism(this);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            unblockOtherThreads();
        }
    }

    public void eat(Square currentSquare) {
        try {
            blockOtherThreads();
            boolean ate = false;
            double maxWeight = OrganismDataTable.getData(this).get("weight");
            List<Organism> organisms = currentSquare.getOrganismList();
            for (Organism organism : organisms) {
                if (!(organism.getName().equals(this.getName()))) {
                    double organismWeight = organism.getWeight();
                    int probability = ProbabilityTable.getProbability(this, organism);

                    int randomNumber = ThreadLocalRandom.current().nextInt(0, 100 + 1);
                    if (randomNumber <= probability) {
                        this.setWeight(this.getWeight() + organismWeight * 0.85);
                        if (this.getWeight() > maxWeight) {
                            this.setWeight(maxWeight);
                        }
                        currentSquare.removeOrganism(organism);
                        ate = true;
                        break;
                    }
                }
            }
            if (!ate) {
                this.setWeight(this.getWeight() * 0.95);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            unblockOtherThreads();
        }
    }

    public void reproduce(Square currentSquare) {
        try {
            blockOtherThreads();
            String organismName = this.getName();
            int maxOnOneSquare = this.getMaxOnOneSquare();

            if (currentSquare.getOrganismCount().get(organismName) >= 2) {
                for (Organism organism : currentSquare.getOrganismList()) {
                    if (organism.getName().equals(organismName)
                            && currentSquare.getOrganismCount().get(organismName) < maxOnOneSquare) {

                        int numberOfChildren = ThreadLocalRandom.current().nextInt(1, 3 + 1);
                        for (int i = 0; i < numberOfChildren; i++) {
                            Animal newOrganism = (Animal) OrganismFactory.newOrganism(organismName);
                            currentSquare.addOrganism(newOrganism);
                        }
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            unblockOtherThreads();
        }
    }
}