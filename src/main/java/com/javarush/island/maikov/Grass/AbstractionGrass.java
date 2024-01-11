package com.javarush.island.maikov.Grass;

import com.javarush.island.maikov.Abstraction.Grass;

import java.util.Objects;

public class AbstractionGrass extends Grass {
    private int weight;
    private int maxAnimalOnSpace;
    private int x;
    private int y;
    private Thread thread;

    public AbstractionGrass(int weight, int maxAnimalOnSpace, int x, int y) {
        this.weight = weight;
        this.maxAnimalOnSpace = maxAnimalOnSpace;
        this.x = x;
        this.y = y;
        this.thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractionGrass that = (AbstractionGrass) o;

        if (weight != that.weight) return false;
        if (maxAnimalOnSpace != that.maxAnimalOnSpace) return false;
        if (x != that.x) return false;
        if (y != that.y) return false;
        return Objects.equals(thread, that.thread);
    }

    @Override
    public int hashCode() {
        int result = weight;
        result = 31 * result + maxAnimalOnSpace;
        result = 31 * result + x;
        result = 31 * result + y;
        result = 31 * result + (thread != null ? thread.hashCode() : 0);
        return result;
    }
}
