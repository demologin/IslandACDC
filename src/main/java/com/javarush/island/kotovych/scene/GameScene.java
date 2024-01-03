package com.javarush.island.kotovych.scene;

import com.javarush.island.kotovych.organisms.Organism;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class GameScene {
    @Getter
    @Setter
    private Square[][] field;

    private Map<Integer[], Square> squareMap;
    public GameScene(int height, int width){
        field = new Square[width][height];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                field[x][y] = new Square(x, y);
            }
        }
    }

    public void moveAnimals(Square source){

    }

    private Square getCurrentSquare(Organism source){
        return squareMap.get(new Integer[]{source.getX(), source.getY()});
    }
}
