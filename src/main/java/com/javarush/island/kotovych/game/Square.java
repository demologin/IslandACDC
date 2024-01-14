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
            String name = organism.getName();
            organismCount.putIfAbsent(name, new AtomicInteger(0));
            if (this.getOrganismList().size() < Settings.getMaxAnimalsOnSquare()
                    && (getOrganismCount().get(name) == null || getOrganismCount().get(name).get() < organism.getMaxOnOneSquare()
            )
            ) {
                organismList.add(organism);
                organismCount.get(name).getAndIncrement();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            unblockOtherThreads();
        }
    }

    public void removeOrganism(Organism organism) {
        try {
            blockOtherThreads();
            if (organismList.remove(organism)) {
                String name = organism.getName();
                organismCount.get(name).decrementAndGet();
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

    private void blockOtherThreads() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void unblockOtherThreads() {
        if (semaphore.availablePermits() == 0) {
            semaphore.release();
        }
    }
}
