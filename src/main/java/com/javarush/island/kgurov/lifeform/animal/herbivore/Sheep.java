package com.javarush.island.kgurov.lifeform.animal.herbivore;

import com.javarush.island.kgurov.field.Field;
import com.javarush.island.kgurov.field.GameMap;
import com.javarush.island.kgurov.lifeform.animal.Animal;

public class Sheep extends Herbivore {
    public Sheep() {
        super(70, 3, 15, 140, "Sheep");
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Sheep){
            Field location = GameMap.getInstance()
                    .getLocation(partner.getRow(), partner.getCol());
            GameMap.getInstance()
                    .addAnimal(new Sheep(), location.getRow(), location.getCol());
        }
    }
}
