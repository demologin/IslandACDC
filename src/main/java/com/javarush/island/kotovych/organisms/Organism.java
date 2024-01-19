package com.javarush.island.kotovych.organisms;

import com.javarush.island.kotovych.exceptions.AppException;
import com.javarush.island.kotovych.game.Square;
import com.javarush.island.kotovych.util.EmojiTable;
import com.javarush.island.kotovych.util.OrganismDataTable;
import com.javarush.island.kotovych.util.Rnd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "name")
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

    private boolean ate;

    @Override
    public Organism clone() throws CloneNotSupportedException {
        Organism clone = (Organism) super.clone();
        clone.id = idCounter.incrementAndGet();
        double maxWeight = OrganismDataTable.getData(this.getName()).get("weight");
        clone.setWeight(Rnd.nextDouble(maxWeight / 2, maxWeight));
        return clone;
    }

    public Organism(){
        setName(this.getClass().getSimpleName());
        Map<String, Double> data = OrganismDataTable.getData(this.getName());
        setWeight(Rnd.nextDouble(data.get("weight") / 2, data.get("weight")));
        setMaxOnOneSquare(data.get("maxOnOneSquare").intValue());
        setMaxStepSize(data.get("maxStepSize").intValue());
        setKilogramsOfFoodNeeded(data.get("kilogramsOfFoodNeeded"));
        setEmoji(EmojiTable.getEmoji(this.getName()));
    }

    public void die(Flock flock, Square square){
        flock.removeOrganism(this, square);
    }

    protected void blockOtherThreadsInOrganism() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new AppException(e);
        }
    }

    protected void unblockOtherThreadsInOrganism(){
        if(semaphore.availablePermits() == 0){
            semaphore.release();
        }
    }

    public void addWeight(double value){
        this.weight += value;
    }
}
