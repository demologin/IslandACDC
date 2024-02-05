package com.javarush.island.motyrev.entities.posibilities;

import com.javarush.island.motyrev.entities.Entity;

import java.util.List;

public interface Eatable {
    List<Entity> eat(List<Entity> entityInCell);
}
