package com.javarush.island.kgurov.lifeform.animal.predator;

import com.javarush.island.kgurov.field.Field;
import com.javarush.island.kgurov.field.GameMap;
import com.javarush.island.kgurov.lifeform.animal.Animal;

import java.util.Map;

public class Bear extends Predator {
    public Bear() {
        super(500, 2, 80, 5, "Bear");
    }
    @Override
    public double getChanceToEat(String foodName) {
        Map<String, Double> chances = Map.of(
                "Duck", 0.1,
                "Buffalo", 0.2,
                "Horse", 0.4,
                "WildBoar", 0.5,
                "Goat", 0.7,
                "Sheep", 0.7,
                "Deer", 0.8,
                "Rabbit", 0.8,
                "Snake", 0.8,
                "Mouse", 0.9
        );
        return chances.getOrDefault(foodName, 0.0);
    }
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Bear){
            Field location = GameMap.getInstance()
                    .getLocation(partner.getRow(), partner.getCol());
            GameMap.getInstance()
                    .addAnimal(new Bear(), location.getRow(), location.getCol());
        }
    }
}
