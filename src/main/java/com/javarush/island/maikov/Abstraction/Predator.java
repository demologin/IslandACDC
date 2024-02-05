package com.javarush.island.maikov.Abstraction;

import java.util.Objects;

public abstract class Predator extends AnimalsWorker {
    private int weight;
    private int maxAnimalOnSpace;
    private int maxSpeed;
    private double maxFood;
    private int x;
    private int y;
    private Thread thread;
    private volatile double life;



    protected Predator(int weight, int maxAnimalOnSpace, int maxSpeed, double maxFood, int x, int y) {
        this.weight = weight;
        this.maxAnimalOnSpace = maxAnimalOnSpace;
        this.maxSpeed = maxSpeed;
        this.maxFood = maxFood;
        this.x = x;
        this.y = y;
        life = maxFood;
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

    public double getLife() {
        return life;
    }

    public void setLife(double life) {
        this.life = life;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Predator predator = (Predator) o;

        if (weight != predator.weight) return false;
        if (maxAnimalOnSpace != predator.maxAnimalOnSpace) return false;
        if (maxSpeed != predator.maxSpeed) return false;
        if (Double.compare(maxFood, predator.maxFood) != 0) return false;
        if (x != predator.x) return false;
        if (y != predator.y) return false;
        if (Double.compare(life, predator.life) != 0) return false;
        return Objects.equals(thread, predator.thread);
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
        result = 31 * result + x;
        result = 31 * result + y;
        result = 31 * result + (thread != null ? thread.hashCode() : 0);
        temp = Double.doubleToLongBits(life);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
