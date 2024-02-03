package com.javarush.island.kgurov.lifeform.animal.herbivore;

import com.javarush.island.kgurov.field.Field;
import com.javarush.island.kgurov.field.GameMap;
import com.javarush.island.kgurov.lifeform.animal.Animal;

public class Rabbit extends Herbivore {
    public Rabbit() {
        super(2, 2, 0.45, 150, "Rabbit");
    }
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Rabbit){
            Field location = GameMap.getInstance().getLocation(partner.getRow(), partner.getCol());
            GameMap.getInstance().addAnimal(new Rabbit(), location.getRow(), location.getCol());
        }
    }
}
