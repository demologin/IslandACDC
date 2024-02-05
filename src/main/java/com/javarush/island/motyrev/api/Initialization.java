package com.javarush.island.motyrev.api;

import com.javarush.island.motyrev.entities.Entity;
import com.javarush.island.motyrev.field.Cell;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Initialization {
    private final Cell[][] FIELD = new Cell[Parameters.HORIZONTAL_GAME_FIELD_SIZE][Parameters.VERTICAL_GAME_FIELD_SIZE];

    public Cell[][] getFIELD() {
        initialize();
        return FIELD;
    }

    private void initialize() {
        EntityGenerator generator = new EntityGenerator();
        for (int i = 0; i < FIELD.length; i++) {
            for (int j = 0; j < FIELD[i].length; j++) {
                List<Entity> entityInCell = new LinkedList<>();
                for (Map.Entry<Class<? extends Entity>, Integer> limitEntrySet : Parameters.LIMIT_ENTITIES_IN_CELL.entrySet()) {
                    entityInCell.addAll(generator.createListNewEntity(limitEntrySet.getKey(), limitEntrySet.getValue()/2));
                }
                FIELD[i][j] = new Cell(entityInCell);
            }
        }
    }


}
