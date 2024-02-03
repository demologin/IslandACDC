package com.javarush.island.kgurov.lifeform.animal.predator;

import com.javarush.island.kgurov.field.Field;
import com.javarush.island.kgurov.field.GameMap;
import com.javarush.island.kgurov.lifeform.animal.Animal;

import java.util.Map;

public class Wolf extends Predator {
    public Wolf() {
        super(50, 3, 8, 30, "Wolf");
    }
    @Override
    public double getChanceToEat(String foodName) {
        Map<String, Double> chances = Map.of(
                "Horse", 0.1,
                "Buffalo", 0.1,
                "Deer", 0.15,
                "WildBoar", 0.15,
                "Duck", 0.4,
                "Goat", 0.6,
                "Rabbit", 0.6,
                "Sheep", 0.7,
                "Mouse", 0.8
        );
        return chances.getOrDefault(foodName, 0.0);
    }
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Wolf) {
            Field location = GameMap.getInstance()
                    .getLocation(partner.getRow(), partner.getCol());
            GameMap.getInstance()
                    .addAnimal(new Wolf(), location.getRow(), location.getCol());
        }
    }
}
