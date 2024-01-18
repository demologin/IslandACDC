package com.javarush.island.berezovskiy.Entities.Organism.Animals.Herbivores;

import com.javarush.island.berezovskiy.Configs.Configs;
import com.javarush.island.berezovskiy.Constants.Constants;
import java.util.concurrent.atomic.AtomicInteger;

public class Rabbit extends Herbivorous{
    public static AtomicInteger getRabbitNumber() {
        return rabbitNumber;
    }

    protected static AtomicInteger rabbitNumber = new AtomicInteger();

    public Rabbit(){
        this.maximumCount = Configs.MAX_RABBIT_COUNT_IN_CELL;
        this.name = Constants.RABBIT;
        maximumStep = Configs.MAX_STEP_RABBIT;
        rabbitNumber.incrementAndGet();
    }

    @Override
    public void incrementOrganismCount() {
        Rabbit.rabbitNumber.incrementAndGet();
    }

    @Override
    public void decrementOrganismCount() {
       Rabbit.rabbitNumber.decrementAndGet();
    }
}
