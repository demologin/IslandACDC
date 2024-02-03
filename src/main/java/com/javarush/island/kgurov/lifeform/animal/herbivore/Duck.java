package com.javarush.island.kgurov.lifeform.animal.herbivore;

import com.javarush.island.kgurov.field.Field;
import com.javarush.island.kgurov.field.GameMap;
import com.javarush.island.kgurov.lifeform.animal.Animal;

import java.util.Map;

public class Duck extends Herbivore {
    public Duck() {
        super(1, 4, 0.15, 200, "Duck");
    }
    @Override
    public double getChanceToEat(String foodName) {
        Map<String, Double> chances = Map.of(
                "Caterpillar", 0.9,
                "Plant", 1.0
        );
        return chances.getOrDefault(foodName, 0.0);
    }
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Duck){
            Field location = GameMap.getInstance()
                    .getLocation(partner.getRow(), partner.getCol());
            GameMap.getInstance()
                    .addAnimal(new Duck(), location.getRow(), location.getCol());
        }
    }
}
