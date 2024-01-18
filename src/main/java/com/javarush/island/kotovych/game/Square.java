package com.javarush.island.kotovych.game;

import com.javarush.island.kotovych.exceptions.AppException;
import com.javarush.island.kotovych.factory.OrganismFactory;
import com.javarush.island.kotovych.game.statistics.Statistics;
import com.javarush.island.kotovych.organisms.Flock;
import com.javarush.island.kotovych.organisms.Organism;
import com.javarush.island.kotovych.settings.Settings;
import com.javarush.island.kotovych.util.EmojiTable;
import com.javarush.island.kotovych.util.Rnd;
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
    private List<Flock> flockList = new CopyOnWriteArrayList<>();
    private AtomicInteger totalAnimalsInSquare = new AtomicInteger(0);
    private Statistics statistics;

    public Square(int x, int y, Statistics statistics) {
        this.x = x;
        this.y = y;
        this.statistics = statistics;
        fill();
    }

    public boolean addFlock(Flock flock) {
        try {
            blockOtherThreadsInSquare();
            organismCount.putIfAbsent(flock.getName(), new AtomicInteger(0));
            if (totalAnimalsInSquare.get() + flock.getOrganisms().size() < Settings.getMaxAnimalsOnSquare()) {
                flockList.add(flock);
                organismCount.get(flock.getName()).addAndGet(flock.getOrganisms().size());
                totalAnimalsInSquare.addAndGet(flock.getOrganisms().size());
                statistics.addOrganism(flock.getName(), flock.getOrganisms().size());
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new AppException(e);
        } finally {
            unblockOtherThreadsInSquare();
        }
    }

    public void addOrganismToMap(Organism organism){
        totalAnimalsInSquare.incrementAndGet();
        organismCount.get(organism.getName()).incrementAndGet();
        statistics.addOrganism(organism.getName(), 1);
    }

    public void removeFlock(Flock flock) {
        try {
            blockOtherThreadsInSquare();
            if (flockList.remove(flock)) {
                organismCount.get(flock.getName()).addAndGet(-flock.getOrganisms().size());
                totalAnimalsInSquare.addAndGet(-flock.getOrganisms().size());
                statistics.removeOrganism(flock.getName(), flock.getOrganisms().size());
            }
        } catch (Exception e) {
            throw new AppException(e);
        } finally {
            unblockOtherThreadsInSquare();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("""
                Square at (%d, %d) - Entities: %d {
                """.formatted(this.getX(), this.getY(), totalAnimalsInSquare.get()));
        for (Map.Entry<String, AtomicInteger> entry : organismCount.entrySet()) {
            int value = entry.getValue().get();
            if(value != 0) {
                builder.append("\t%s: %d\n".formatted(EmojiTable.getEmoji(entry.getKey()), entry.getValue().get()));
            }
        }
        builder.append("}");
        return builder.toString();
    }

    private void fill() {
        Object[] organisms = OrganismFactory.getOrganismPrototypes().keySet().toArray();
        while (totalAnimalsInSquare.get() != Settings.getAnimalsOnSquareAtTheBeginning()) {
            Flock flock;
            int organismsInFlock = Rnd.nextInt(1, Settings.getMaxFlockSize());
            int organismsInSquareAfterAddingFlock = organismsInFlock + totalAnimalsInSquare.get();
            if (organismsInSquareAfterAddingFlock > Settings.getAnimalsOnSquareAtTheBeginning()) {
                int diff = organismsInSquareAfterAddingFlock - Settings.getAnimalsOnSquareAtTheBeginning();
                organismsInFlock -= diff;
            }
            String organism = (String) organisms[ThreadLocalRandom.current().nextInt(organisms.length)];
            flock = new Flock(organism, organismsInFlock);
            addFlock(flock);
        }
    }

    private void blockOtherThreadsInSquare() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new AppException();
        }
    }

    private void unblockOtherThreadsInSquare() {
        if (semaphore.availablePermits() == 0) {
            semaphore.release();
        }
    }
}
