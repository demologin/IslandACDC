package com.javarush.island.berezovskiy.Entities.Animals;

import com.javarush.island.berezovskiy.Entities.Movable;
import com.javarush.island.berezovskiy.Entities.Organism;

public abstract class Animal extends Organism implements Movable {
    public abstract void eat();
}
