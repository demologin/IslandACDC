package com.javarush.island.kgurov.lifeform.animal.herbivore;

import com.javarush.island.kgurov.field.Field;
import com.javarush.island.kgurov.field.GameMap;
import com.javarush.island.kgurov.lifeform.animal.Animal;

public class Buffalo extends Herbivore {
    public Buffalo() {
        super(700, 3, 100, 10, "Buffalo");
    }
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Buffalo){
            Field location = GameMap.getInstance()
                    .getLocation(partner.getRow(), partner.getCol());
            GameMap.getInstance()
                    .addAnimal(new Buffalo(), location.getRow(), location.getCol());
        }
    }
}
