package com.javarush.island.kgurov.lifeform.animal.herbivore;

import com.javarush.island.kgurov.field.Field;
import com.javarush.island.kgurov.field.GameMap;
import com.javarush.island.kgurov.lifeform.animal.Animal;

public class Horse extends Herbivore {
    public Horse() {
        super(400, 4, 60, 20, "Horse");
    }
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Horse){
            Field location = GameMap.getInstance()
                    .getLocation(partner.getRow(), partner.getCol());
            GameMap.getInstance()
                    .addAnimal(new Horse(), location.getRow(), location.getCol());
        }
    }
}
