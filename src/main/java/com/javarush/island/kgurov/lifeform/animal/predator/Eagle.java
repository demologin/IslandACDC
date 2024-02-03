package com.javarush.island.kgurov.lifeform.animal.predator;

import com.javarush.island.kgurov.field.Field;
import com.javarush.island.kgurov.field.GameMap;
import com.javarush.island.kgurov.lifeform.animal.Animal;

import java.util.Map;

public class Eagle extends Predator {
    public Eagle() {
        super(6, 3, 1, 20, "Eagle");
    }

    @Override
    public double getChanceToEat(String foodName) {
        Map<String, Double> chances = Map.of(
                "Fox", 0.1,
                "Duck", 0.8,
                "Rabbit", 0.9,
                "Mouse", 0.9
        );
        return chances.getOrDefault(foodName, 0.0);
    }
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Eagle) {
            Field location = GameMap.getInstance()
                    .getLocation(partner.getRow(), partner.getCol());
            GameMap.getInstance()
                    .addAnimal(new Eagle(), location.getRow(), location.getCol());
        }
    }
}
