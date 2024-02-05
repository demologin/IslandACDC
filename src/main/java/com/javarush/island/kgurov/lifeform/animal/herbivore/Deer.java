package com.javarush.island.kgurov.lifeform.animal.herbivore;

import com.javarush.island.kgurov.field.Field;
import com.javarush.island.kgurov.field.GameMap;
import com.javarush.island.kgurov.lifeform.animal.Animal;

public class Deer extends Herbivore {
    public Deer() {
        super(300, 4, 50, 20, "Deer");
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Deer){
            Field location = GameMap.getInstance()
                    .getLocation(partner.getRow(), partner.getCol());
            GameMap.getInstance()
                    .addAnimal(new Deer(), location.getRow(), location.getCol());
        }
    }
}
