package com.javarush.island.motyrev;

import com.javarush.island.motyrev.api.Initialization;
import com.javarush.island.motyrev.field.GameField;

public class App {
    public static void main(String[] args){
        GameField gameField = new GameField(new Initialization().getFIELD());
        gameField.revitalizeIsland();
    }
}