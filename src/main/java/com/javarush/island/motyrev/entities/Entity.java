package com.javarush.island.motyrev.entities;

import com.javarush.island.motyrev.api.EntityGenerator;
import com.javarush.island.motyrev.api.Parameters;
import com.javarush.island.motyrev.entities.posibilities.Multipliable;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public abstract class Entity implements Multipliable {
    private double weight;
    private boolean readyMultiply = true;
    private boolean dead = false;

    public Entity(double weight) {
        this.weight = weight;
    }

    @Override
    public List<Entity> multiply(List<Entity> entityInCell) {
        Class<? extends Entity> thisEntityType = this.getClass();
        for (Entity entity : entityInCell) {
            if (this.readyMultiply && this != entity && entity.isReadyMultiply() && thisEntityType == entity.getClass()){
                int randomValue = ThreadLocalRandom.current().nextInt(0, 100);
                int birthProbability = Parameters.BIRTH_PROBABILITY.get(thisEntityType);
                this.readyMultiply = false;
                entity.setReadyMultiply(false);
                if (birthProbability > randomValue){
                    EntityGenerator generator = new EntityGenerator();
                    int birthAmount = Parameters.BIRTH_MAX_AMOUNT.get(thisEntityType);
                    return generator.createListNewEntity(thisEntityType, birthAmount);
                }else {
                    return null;
                }
            }
        }
        return null;
    }

    public void setReadyMultiply(boolean readyMultiply) {
        this.readyMultiply = readyMultiply;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
