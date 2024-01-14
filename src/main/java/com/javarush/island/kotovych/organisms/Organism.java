package com.javarush.island.kotovych.organisms;

import com.javarush.island.kotovych.exceptions.AppException;
import com.javarush.island.kotovych.game.Square;
import com.javarush.island.kotovych.util.EmojiTable;
import com.javarush.island.kotovych.util.OrganismDataTable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public abstract class Organism implements Cloneable{
    private static AtomicLong idCounter = new AtomicLong(System.currentTimeMillis());
    private long id = idCounter.incrementAndGet();

    private String emoji;
    private String name;
    private double weight;
    private int maxOnOneSquare;
    private int maxStepSize;
    private double kilogramsOfFoodNeeded;

    private Semaphore semaphore = new Semaphore(1);

    @Override
    public Organism clone() throws CloneNotSupportedException {
        Organism clone = (Organism) super.clone();
        clone.id = idCounter.incrementAndGet();
        double maxWeight = OrganismDataTable.getData(this).get("weight");
        clone.setWeight(ThreadLocalRandom.current().nextDouble(maxWeight / 2, maxWeight));
        return clone;
    }

    public Organism(){
        setName(this.getClass().getSimpleName());
        Map<String, Double> data = OrganismDataTable.getData(this);
        setWeight(ThreadLocalRandom.current().nextDouble(data.get("weight") / 2, data.get("weight")));
        setMaxOnOneSquare(data.get("maxOnOneSquare").intValue());
        setMaxStepSize(data.get("maxStepSize").intValue());
        setKilogramsOfFoodNeeded(data.get("kilogramsOfFoodNeeded"));
        setEmoji(EmojiTable.getEmoji(this.getName()));
    }

    public void die(Square currentSquare){
        currentSquare.removeOrganism(this);
    }

    protected void blockOtherThreads() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void unblockOtherThreads(){
        if(semaphore.availablePermits() == 0){
            semaphore.release();
        }
    }
}
