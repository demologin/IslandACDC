package com.javarush.island.maikov.Animals.Herbivore;

import com.javarush.island.maikov.Abstraction.Animals;

import java.util.Objects;
import java.util.concurrent.Semaphore;

public abstract class Herbivore extends Animals {
    private int weight;
    private int maxAnimalOnSpace;
    private int maxSpeed;
    private double maxFood;
    private Enum anEnum;
    private int x;
    private int y;
    private Thread thread;
    private volatile double live;


    protected Herbivore(int weight, int maxAnimalOnSpace, int maxSpeed, double maxFood, Enum anEnum, int x, int y) {
        this.weight = weight;
        this.maxAnimalOnSpace = maxAnimalOnSpace;
        this.maxSpeed = maxSpeed;
        this.maxFood = maxFood;
        this.anEnum = anEnum;
        this.x = x;
        this.y = y;
        live = maxFood;
        thread = new Thread(this);
        thread.start();
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMaxAnimalOnSpace() {
        return maxAnimalOnSpace;
    }

    public void setMaxAnimalOnSpace(int maxAnimalOnSpace) {
        this.maxAnimalOnSpace = maxAnimalOnSpace;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getMaxFood() {
        return maxFood;
    }

    public void setMaxFood(double maxFood) {
        this.maxFood = maxFood;
    }

    public Enum getAnEnum() {
        return anEnum;
    }

    public void setAnEnum(Enum anEnum) {
        this.anEnum = anEnum;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public double getLive() {
        return live;
    }

    public void setLive(double live) {
        this.live = live;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Herbivore herbivore = (Herbivore) o;

        if (weight != herbivore.weight) return false;
        if (maxAnimalOnSpace != herbivore.maxAnimalOnSpace) return false;
        if (maxSpeed != herbivore.maxSpeed) return false;
        if (Double.compare(maxFood, herbivore.maxFood) != 0) return false;
        if (x != herbivore.x) return false;
        if (y != herbivore.y) return false;
        if (Double.compare(live, herbivore.live) != 0) return false;
        if (!Objects.equals(anEnum, herbivore.anEnum)) return false;
        return Objects.equals(thread, herbivore.thread);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = weight;
        result = 31 * result + maxAnimalOnSpace;
        result = 31 * result + maxSpeed;
        temp = Double.doubleToLongBits(maxFood);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (anEnum != null ? anEnum.hashCode() : 0);
        result = 31 * result + x;
        result = 31 * result + y;
        result = 31 * result + (thread != null ? thread.hashCode() : 0);
        temp = Double.doubleToLongBits(live);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
