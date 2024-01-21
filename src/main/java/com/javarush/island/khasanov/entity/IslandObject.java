package com.javarush.island.khasanov.entity;

import com.javarush.island.khasanov.repository.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public abstract class IslandObject {
    public static AtomicInteger counter = new AtomicInteger();
    private int ID;
    private double weight;
    private int maxCountOnField;
    private AtomicInteger hp;
    private AtomicBoolean isAlive;
    private AtomicBoolean isHere;
    private AtomicBoolean readyForReproduce;
    private String className;

    protected Position position;
    protected Island island;

    protected IslandObject(Island island, Position position) {
        setClassName(this.getClass().getSimpleName());
        this.position = position;
        this.island = island;
        createNewId();
        isAlive = new AtomicBoolean(true);
        isHere = new AtomicBoolean(true);
        readyForReproduce = new AtomicBoolean(true);
    }

    protected IslandObject() {
        setClassName(this.getClass().getSimpleName());
    }

    public abstract List<IslandObject> eat();

    public abstract Position move();

    public abstract IslandObject reproduce();

    public boolean isHereAndIsAlive() {
        return isHere.get() && isAlive.get();
    }


    public void die() {
        isAlive.set(false);
    }

    public void resetReadyForReproduce() {
        setReadyForReproduce(new AtomicBoolean(true));
    }

    public abstract void strave();

    public abstract void saturate(double weight);

    public AtomicInteger calculateHpChanging(int delta) {
        int result = hp.addAndGet(delta);
        result = Math.min(result, 100);
        result = Math.max(result, 0);
        return new AtomicInteger(result);
    }

    public abstract IslandObject copyOf(IslandObject object);

    @Override
    public String toString() {
        return className + "{" +
                "id=" + ID +
                " hp=" + hp +
                '}';
    }

    public void createNewId() {
        ID = counter.incrementAndGet();
    }

}
