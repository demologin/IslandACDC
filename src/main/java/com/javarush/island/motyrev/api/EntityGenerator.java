package com.javarush.island.motyrev.api;

import com.javarush.island.motyrev.entities.Entity;
import com.javarush.island.motyrev.entities.plants.Plants;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class EntityGenerator {

    public List<Entity> createListNewEntity(Class<? extends Entity> clazzEntity, int entityAmount) {
        List<Entity> entityInCell = new LinkedList<>();
            for (int i = 0; i < entityAmount; i++) {
                entityInCell.add(createEntity(clazzEntity));
            }
        return entityInCell;
    }

    private Entity createEntity(Class<? extends Entity> clazzEntity) {
        try {
            if (clazzEntity.getSuperclass() == Plants.class) {
                return clazzEntity.getDeclaredConstructor(double.class)
                        .newInstance(Parameters.ENTITY_WEIGHT.get(clazzEntity));
            } else {
                int sex = ThreadLocalRandom.current().nextInt(0, 2);
                double weight = Parameters.ENTITY_WEIGHT.get(clazzEntity);
                int speed = Parameters.ENTITY_SPEED.get(clazzEntity);
                double canEatForOneTime = Parameters.ENTITY_CAN_EAT_FOR_ONE_TIME.get(clazzEntity);
                double loseWeight = Parameters.ENTITY_LOSE_WEIGHT.get(clazzEntity);
                Map<Class<? extends Entity>, Integer> eatProbability = Parameters.EAT_PROBABILITY.get(clazzEntity);
                Class<?>[] animalClassParams = {int.class, double.class, int.class, double.class, double.class, Map.class};

                return clazzEntity.getDeclaredConstructor(animalClassParams)
                        .newInstance(sex, weight, speed, canEatForOneTime, loseWeight, eatProbability);
            }
        } catch (InvocationTargetException | InstantiationException
                 | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
