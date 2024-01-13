package com.javarush.island.maikov.Abstraction;


import com.javarush.island.maikov.Actions.Reproduce;

public abstract class Grass extends Organism {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                Reproduce.reproduce(this);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
