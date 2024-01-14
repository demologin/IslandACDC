package com.javarush.island.motyrev.field;

import com.javarush.island.motyrev.api.Parameters;
import com.javarush.island.motyrev.entities.Entity;
import com.javarush.island.motyrev.entities.animals.Animal;
import com.javarush.island.motyrev.entities.plants.Grass;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Cell implements Runnable{
    private final Map<Class<? extends Entity>, Integer> AMOUNT_ENTITIES_IN_CELL = new HashMap<>();
    private final List<Entity> ENTITY_IN_CELL;

    public Cell(List<Entity> entityInCell) {
        this.ENTITY_IN_CELL = entityInCell;
        initializationEntitiesInCell();
    }

    @Override
    public void run() {
        grassGrow();
        eat();
        multiply();
    }

    public Map<Class<? extends Entity>, Integer> getAmountEntitiesInCell() {
        return AMOUNT_ENTITIES_IN_CELL;
    }

    public List<Entity> getEntityInCell() {
        return ENTITY_IN_CELL;
    }

    private void initializationEntitiesInCell(){
        for (Entity entity : ENTITY_IN_CELL) {
            Class<? extends Entity> entityClass = entity.getClass();
            if (AMOUNT_ENTITIES_IN_CELL.containsKey(entityClass)){
                int newLimitValue = AMOUNT_ENTITIES_IN_CELL.get(entityClass) + 1;
                AMOUNT_ENTITIES_IN_CELL.put(entityClass, newLimitValue);
            }else {
                AMOUNT_ENTITIES_IN_CELL.put(entityClass, 1);
            }
        }
    }

    private void multiply() {
        List<Entity> newEntities = new LinkedList<>();
        for (Entity entity : ENTITY_IN_CELL) {
            Class<? extends Entity> entityClass = entity.getClass();
            int thisEntityAmountInCell = AMOUNT_ENTITIES_IN_CELL.get(entityClass);
            int limitThisEntityAmountInCell = Parameters.LIMIT_ENTITIES_IN_CELL.get(entityClass);
            if (thisEntityAmountInCell < limitThisEntityAmountInCell){
                 List<Entity> result = entity.multiply(ENTITY_IN_CELL);
                 if (result != null){
                     newEntities.addAll(result);
                     int newAmount = thisEntityAmountInCell + result.size();
                     AMOUNT_ENTITIES_IN_CELL.put(entityClass, newAmount);
                 }
            }
        }
        ENTITY_IN_CELL.addAll(newEntities);
    }

    private void eat() {
        List <Entity> entitiesWasEaten = new LinkedList<>();
        for (Entity entity : ENTITY_IN_CELL) {
            if (entity.getClass().getSuperclass() == Animal.class){
                Animal animal = (Animal) entity;
                List<Entity> dieEntities = animal.eat(ENTITY_IN_CELL);
                if (dieEntities != null){
                    entitiesWasEaten.addAll(dieEntities);
                }
            }
        }
        for (Entity dieEntity : entitiesWasEaten) {
            Class<? extends Entity> entityClass = dieEntity.getClass();
            int newAmount = AMOUNT_ENTITIES_IN_CELL.get(entityClass) - 1;
            AMOUNT_ENTITIES_IN_CELL.put(entityClass, newAmount);
            ENTITY_IN_CELL.remove(dieEntity);
        }
    }

    private void grassGrow() {
        for (int i = 0; i < 50; i++) {
            ENTITY_IN_CELL.add(new Grass(Parameters.ENTITY_WEIGHT.get(Grass.class)));
            AMOUNT_ENTITIES_IN_CELL.put(Grass.class, (AMOUNT_ENTITIES_IN_CELL.get(Grass.class)+1));
        }
    }
}
