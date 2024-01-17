package com.javarush.island.khasanov.entity;

import com.javarush.island.khasanov.repository.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class IslandObject {
    public static int counter;
    private int ID;
    private double weight;
    private int maxCountOnField;
    private boolean isAlive;
    private String className;

    protected Position position;
    protected Island island;

    protected IslandObject(Island island, Position position) {
        setClassName(this.getClass().getSimpleName());
        island.incrementObjectOnField(position, this);
        this.position = position;
        this.island = island;
        createNewId();
        isAlive = true;
    }

    protected IslandObject() {
        createNewId();
        setClassName(this.getClass().getSimpleName());
        isAlive = true;
    }

    public void die() {
        isAlive = false;
    }

    public abstract IslandObject copyOf(IslandObject object);

    @Override
    public String toString() {
        return className + "{" +
                "id=" + ID +
                '}';
    }

    public void createNewId() {
        ID = ++counter;
    }

}
