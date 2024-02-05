package com.javarush.island.kgurov.lifeform.animal.herbivore;

import com.javarush.island.kgurov.field.Field;
import com.javarush.island.kgurov.field.GameMap;
import com.javarush.island.kgurov.lifeform.animal.Animal;

public class Caterpillar extends Herbivore {
    public Caterpillar() {
        super(0.01, 0, 0, 1000, "Caterpillar");
    }
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Caterpillar){
            Field location = GameMap.getInstance()
                    .getLocation(partner.getRow(), partner.getCol());
            GameMap.getInstance()
                    .addAnimal(new Caterpillar(), location.getRow(), location.getCol());
        }
    }
}
