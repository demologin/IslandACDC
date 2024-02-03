package com.javarush.island.kgurov.lifeform.animal.predator;

import com.javarush.island.kgurov.field.Field;
import com.javarush.island.kgurov.field.GameMap;
import com.javarush.island.kgurov.lifeform.animal.Animal;

import java.util.Map;

public class Snake extends Predator {
    public Snake() {
        super(15, 1, 3, 30, "Snake");
    }
    @Override
    public double getChanceToEat(String foodName) {
        Map<String, Double> chances = Map.of(
                "Duck", 0.1,
                "Fox", 0.15,
                "Rabbit", 0.2,
                "Mouse", 0.4
        );
        return chances.getOrDefault(foodName, 0.0);
    }
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Snake) {
            Field location = GameMap.getInstance()
                    .getLocation(partner.getRow(), partner.getCol());
            GameMap.getInstance()
                    .addAnimal(new Snake(), location.getRow(), location.getCol());
        }
    }
}
