package com.javarush.island.kotovych.organisms.animals;

import com.javarush.island.kotovych.exceptions.AppException;
import com.javarush.island.kotovych.factory.OrganismFactory;
import com.javarush.island.kotovych.scene.GameScene;
import com.javarush.island.kotovych.organisms.Organism;
import com.javarush.island.kotovych.scene.Square;
import com.javarush.island.kotovych.util.Direction;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public abstract class Animal extends Organism {
    private boolean moved = false;

    public void move(Square currentSquare, GameScene gameScene){
        Direction direction = Direction.values()[ThreadLocalRandom.current().nextInt(Direction.values().length)];
        int stepSize = ThreadLocalRandom.current().nextInt(1, this.getMaxStepSize()  + 2);

        int squareX = currentSquare.getX();
        int squareY = currentSquare.getY();

        switch (direction){
            case UP -> moveToCoordinates(squareX, squareY + stepSize, currentSquare, gameScene);
            case RIGHT -> moveToCoordinates(squareX + stepSize, squareY, currentSquare, gameScene);
            case DOWN -> moveToCoordinates(squareX, squareY - stepSize, currentSquare, gameScene);
            case LEFT -> moveToCoordinates(squareX - stepSize, squareY, currentSquare, gameScene);
        }
    }

    public void eat(Square currentSquare){

    }

    public void reproduce(Square currentSquare){

    }

    private void moveToCoordinates(int x, int y, Square currentSquare, GameScene gameScene){
        try{
            Square neededSquare = gameScene.getSquareByCoordinates(x, y);
            currentSquare.removeOrganism(this);
            neededSquare.addOrganism(this);
            setMoved(true);
        } catch (ArrayIndexOutOfBoundsException e){
            move(currentSquare, gameScene);
        }
    }

    public void reproduce(Square currentSquare, GameScene gameScene){
        currentSquare.addOrganism(OrganismFactory.newOrganism(this.getName()));
    }
}
