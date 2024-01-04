package com.javarush.island.kotovych.scene;

import com.javarush.island.kotovych.organisms.animals.Animal;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;


@Getter
@Setter
public class GameScene {
    private Square[][] field;

    public GameScene(int width, int height){
        field = new Square[width][height];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                field[x][y] = new Square(x, y);
            }
        }
    }


    public Square getSquareByCoordinates(int x, int y){
        return field[x][y];
    }
}
