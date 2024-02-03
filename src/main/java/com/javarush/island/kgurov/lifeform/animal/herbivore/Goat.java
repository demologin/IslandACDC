package com.javarush.island.kgurov.lifeform.animal.herbivore;

import com.javarush.island.kgurov.field.Field;
import com.javarush.island.kgurov.field.GameMap;
import com.javarush.island.kgurov.lifeform.animal.Animal;

public class Goat extends Herbivore {
    public Goat() {
        super(60, 3, 10, 140, "Goat");
    }
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Goat){
            Field location = GameMap.getInstance()
                    .getLocation(partner.getRow(), partner.getCol());
            GameMap.getInstance()
                    .addAnimal(new Goat(), location.getRow(), location.getCol());
        }
    }
}
