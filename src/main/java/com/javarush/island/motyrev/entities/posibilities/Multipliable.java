package com.javarush.island.motyrev.entities.posibilities;

import com.javarush.island.motyrev.entities.Entity;

import java.util.List;

public interface Multipliable {
    List<Entity> multiply(List<Entity> entityInCell);
}
