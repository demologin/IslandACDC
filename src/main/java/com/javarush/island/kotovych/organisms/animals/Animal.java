package com.javarush.island.kotovych.organisms.animals;

import com.javarush.island.kotovych.exceptions.AppException;
import com.javarush.island.kotovych.factory.OrganismFactory;
import com.javarush.island.kotovych.scene.GameScene;
import com.javarush.island.kotovych.organisms.Organism;
import com.javarush.island.kotovych.scene.Square;
import com.javarush.island.kotovych.settings.Settings;
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
    private boolean moved = false;
    private boolean ate = false;
    private boolean reproduced = false;

    public void move(Square currentSquare, GameScene gameScene) {
        if(gameScene.getSquares().size() > 1) {
            Direction direction = Direction.values()[ThreadLocalRandom.current().nextInt(Direction.values().length)];
            int stepSize = ThreadLocalRandom.current().nextInt(0, this.getMaxStepSize() + 2);

            int squareX = currentSquare.getX();
            int squareY = currentSquare.getY();

            switch (direction) {
                case UP -> moveToCoordinates(squareX, squareY + stepSize, currentSquare, gameScene);
                case RIGHT -> moveToCoordinates(squareX + stepSize, squareY, currentSquare, gameScene);
                case DOWN -> moveToCoordinates(squareX, squareY - stepSize, currentSquare, gameScene);
                case LEFT -> moveToCoordinates(squareX - stepSize, squareY, currentSquare, gameScene);
            }
        }
    }

    public void eat(Square currentSquare) {
        double maxWeight = OrganismDataTable.getData(this).get("weight");
        List<Organism> organisms = currentSquare.getOrganismList();
        for (Organism organism : organisms) {
            if (!(organism.getName().equals(this.getName()))) {
                double organismWeight = organism.getWeight();
                int probability = ProbabilityTable.getProbability(this, organism);

                int randomNumber = ThreadLocalRandom.current().nextInt(0, 100 + 1);
                if (randomNumber <= probability) {
                    this.setWeight(this.getWeight() + organismWeight * 0.85);
                    if(this.getWeight() > maxWeight){
                        this.setWeight(maxWeight);
                    }
                    currentSquare.removeOrganism(organism);
                    setAte(true);
                    break;
                }
            }
        }
        if(!isAte()){
            this.setWeight(this.getWeight() * 0.95);
        }
    }

    public void reproduce(Square currentSquare) {
        if(currentSquare.getOrganismCount().get(this.getName()) >= 2) {
            List<Organism> organisms = currentSquare.getOrganismList();
            for (Organism organism : organisms) {
                if (organism.getName().equals(this.getName())
                        && currentSquare.getOrganismCount().get(this.getName()) < this.getMaxOnOneSquare()
                        && !this.isReproduced()
                        && !((Animal) organism).isReproduced()
                ) {
                    for(int i = 0; i < ThreadLocalRandom.current().nextInt(1, 3 + 1); i++){
                        Animal newOrganism = (Animal) OrganismFactory.newOrganism(this.getName());
                        newOrganism.setReproduced(true);
                        currentSquare.addOrganism(newOrganism);
                    }
                    this.setReproduced(true);
                    ((Animal) organism).setReproduced(true);
                    break;
                }
            }
        }
    }

    private void moveToCoordinates(int x, int y, Square currentSquare, GameScene gameScene) {
        try {
            Square neededSquare = gameScene.getSquareByCoordinates(x, y);
            try {
                neededSquare.addOrganism(this);
                currentSquare.removeOrganism(this);
                setMoved(true);
            } catch (AppException e){
                move(currentSquare, gameScene);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            move(currentSquare, gameScene);
        }
    }
}
