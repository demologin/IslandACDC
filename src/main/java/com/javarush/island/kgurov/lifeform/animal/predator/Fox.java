package com.javarush.island.kgurov.lifeform.animal.predator;

import com.javarush.island.kgurov.field.Field;
import com.javarush.island.kgurov.field.GameMap;
import com.javarush.island.kgurov.lifeform.animal.Animal;

import java.util.Map;

public class Fox extends Predator {
    public Fox() {
        super(8, 2, 2, 30, "Fox");
    }
    @Override
    public double getChanceToEat(String foodName) {
        Map<String, Double> chances = Map.of(
                "Caterpillar", 0.4,
                "Duck", 0.6,
                "Rabbit", 0.7,
                "Mouse", 0.9
        );
        return chances.getOrDefault(foodName, 0.0);
    }
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Fox){
            Field location = GameMap.getInstance()
                    .getLocation(partner.getRow(), partner.getCol());
            GameMap.getInstance()
                    .addAnimal(new Fox(), location.getRow(), location.getCol());
        }
    }
}
