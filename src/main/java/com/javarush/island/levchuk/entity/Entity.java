package com.javarush.island.levchuk.entity;

import com.javarush.island.levchuk.IslandMap.Location;
import com.javarush.island.levchuk.LiveActions.Reproductive;
import com.javarush.island.levchuk.utils.EntityFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Getter
@Setter
public abstract class Entity implements Reproductive {
    private String name;
    private String icon;
    private int amountMax;
    private boolean isReproduced = false;

    public boolean isReproduced() {
        return isReproduced;
    }

    @Override
    public <T extends Entity> T reproduce(Location location) {
        if (this.isReproduced) {
            return null;
        }
        List<Entity> sameEntities = location.getEntities().get(this.getClass());
        if (sameEntities.size() < this.getAmountMax()) {
            this.setReproduced(true);
            Optional<Entity> nextParent = sameEntities.stream().filter(Predicate.not(Entity::isReproduced)).findFirst();
            if (nextParent.isPresent()) {
                T newEntity = (T) EntityFactory.getEntityClass(this.getClass());
                nextParent.get().setReproduced(true);
                newEntity.setReproduced(true);
                return newEntity;
            }
        }
        return null;
    }
}
