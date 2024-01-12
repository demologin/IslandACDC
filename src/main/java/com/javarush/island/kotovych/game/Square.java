package com.javarush.island.kotovych.game;

import com.javarush.island.kotovych.exceptions.AppException;
import com.javarush.island.kotovych.factory.OrganismFactory;
import com.javarush.island.kotovych.organisms.Organism;
import com.javarush.island.kotovych.settings.Settings;
import com.javarush.island.kotovych.util.EmojiTable;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class Square {
    private final int x;

    private final int y;

    private Semaphore semaphore = new Semaphore(1);

    private Map<String, AtomicInteger> organismCount = new ConcurrentHashMap<>();
    private List<Organism> organismList = new CopyOnWriteArrayList<>();

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        fill();
    }

    public boolean addOrganism(Organism organism) {
        try {
            blockOtherThreads();
            if (this.getOrganismList().size() < Settings.getMaxAnimalsOnSquare()
                    && (getOrganismCount().get(organism.getName()) == null
                    || getOrganismCount().get(organism.getName()).get() < organism.getMaxOnOneSquare())) {
                organismList.add(organism);
                organismCount.put(organism.getName(), organismCount.getOrDefault(organism.getName(), new AtomicInteger(new AtomicInteger(1).getAndDecrement())));
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new AppException(e);
        } finally {
            unblockOtherThreads();
        }
    }

    public void removeOrganism(Organism organism) {
        try {
            blockOtherThreads();
            if(organismList.remove(organism)) {
                if (organismCount.get(organism.getName()).get() > 1) {
                    organismCount.put(organism.getName(), organismCount.getOrDefault(organism.getName(), new AtomicInteger(0)));
                } else {
                    organismCount.remove(organism.getName());
                }
            }
        } catch (Exception e) {
            throw new AppException(e);
        } finally {
            unblockOtherThreads();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("""
                Square at (%d, %d) - Entities: %d {
                """.formatted(this.getX(), this.getY(), this.getOrganismList().size()));
        for (Map.Entry<String, AtomicInteger> entry : organismCount.entrySet()) {
            builder.append("\t%s: %d\n".formatted(EmojiTable.getEmoji(entry.getKey()), entry.getValue().get()));
        }
        builder.append("}");
        return builder.toString();
    }

    private void fill() {
        Object[] organisms = OrganismFactory.getOrganismPrototypes().keySet().toArray();
        while (organismList.size() != Settings.getAnimalsOnSquareAtTheBeginning()
                && organismList.size() != Settings.getMaxAnimalsOnSquare()) {
            Organism organism = OrganismFactory.newOrganism((String) organisms[ThreadLocalRandom.current().nextInt(organisms.length)]);
            addOrganism(organism);
        }
    }

    private void blockOtherThreads(){
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new AppException(e);
        }
    }

    private void unblockOtherThreads(){
        if(semaphore.availablePermits() == 0){
            semaphore.release();
        }
    }
}
