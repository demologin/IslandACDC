package com.javarush.island.levchuk.entity.organisms.plants;

import com.javarush.island.levchuk.entity.Entity;

public class Plant extends Entity {
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "name=" + getName() +
                ", icon=" + getIcon() +
                ", amountMax=" + getAmountMax() +
                '}';
    }
}
