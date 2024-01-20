package com.javarush.island.maikov.Abstraction;


import com.javarush.island.maikov.Actions.Reproduce;

public abstract class GrassWorker extends Organism {
    private final Reproduce reproduce = new Reproduce();
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                reproduce.startReproduce(this);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
