package com.javarush.island.kotovych.game;

import com.javarush.island.kotovych.exceptions.AppException;
import com.javarush.island.kotovych.factory.OrganismFactory;
import com.javarush.island.kotovych.organisms.Flock;
import com.javarush.island.kotovych.organisms.Organism;
import com.javarush.island.kotovych.settings.Settings;
import com.javarush.island.kotovych.util.EmojiTable;
import com.javarush.island.kotovych.util.Rnd;
import com.javarush.island.kotovych.util.ShowAlert;
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

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        fill();
    }

    public boolean addFlock(Flock flock) {
        try {
            blockOtherThreadsInSquare();
            organismCount.putIfAbsent(flock.getName(), new AtomicInteger(0));
            if (totalAnimalsInSquare.get() + flock.getOrganisms().size() <= Settings.getMaxAnimalsOnSquare()
                    && !flock.getOrganisms().isEmpty()) {
                flockList.add(flock);
                organismCount.get(flock.getName()).addAndGet(flock.getOrganisms().size());
                totalAnimalsInSquare.addAndGet(flock.getOrganisms().size());
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new AppException(e);
        } finally {
            unblockOtherThreadsInSquare();
        }
    }

    public void addOrganismToMap(Organism organism) {
        totalAnimalsInSquare.incrementAndGet();
        organismCount.get(organism.getName()).incrementAndGet();
    }

    public void removeOrganismFromMap(Organism organism){
        totalAnimalsInSquare.decrementAndGet();
        organismCount.get(organism.getName()).decrementAndGet();
    }

    public void removeFlock(Flock flock) {
        try {
            blockOtherThreadsInSquare();
            if (flockList.remove(flock)) {
                if(organismCount.get(flock.getName()).get() - flock.getOrganisms().size() < 0){
                    throw new AppException();
                }
                organismCount.get(flock.getName()).addAndGet(-flock.getOrganisms().size());
                totalAnimalsInSquare.addAndGet(-flock.getOrganisms().size());
            }
        } catch (Exception e) {
            ShowAlert.showErrorWithStacktrace(e.getMessage(), e);
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
            if (value != 0) {
                builder.append("\t%s: %d\n".formatted(EmojiTable.getEmoji(entry.getKey()), entry.getValue().get()));
            }
        }
        builder.append("}");
        return builder.toString();
    }

    private void fill() {
        Object[] organisms = OrganismFactory.getOrganismPrototypes().keySet().toArray();
        int minNumber = Math.min(Settings.getMaxAnimalsOnSquare(), Settings.getAnimalsOnSquareAtTheBeginning());
        while (totalAnimalsInSquare.get() != minNumber){
            Flock flock;
            int organismsInFlock = Rnd.nextInt(1, Settings.getMaxFlockSize() + 1);
            int organismsInSquareAfterAddingFlock = organismsInFlock + totalAnimalsInSquare.get();

            if (organismsInSquareAfterAddingFlock > minNumber) {
                int diff = organismsInSquareAfterAddingFlock - minNumber;
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
            ShowAlert.showErrorWithStacktrace(e.getMessage(), e);
        }
    }

    private void unblockOtherThreadsInSquare() {
        if (semaphore.availablePermits() == 0) {
            semaphore.release();
        }
    }
}
