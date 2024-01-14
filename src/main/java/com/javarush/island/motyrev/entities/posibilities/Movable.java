package com.javarush.island.motyrev.entities.posibilities;

import com.javarush.island.motyrev.entities.Entity;
import com.javarush.island.motyrev.field.Cell;

import java.util.Map;

public interface Movable {
    boolean move(int y, int x, Cell[][] field, Map<Class<? extends Entity>, Integer> amountEntities);
}
