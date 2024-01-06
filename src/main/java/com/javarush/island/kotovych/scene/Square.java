package com.javarush.island.kotovych.scene;

import com.javarush.island.kotovych.exceptions.AppException;
import com.javarush.island.kotovych.factory.OrganismFactory;
import com.javarush.island.kotovych.organisms.Organism;
import com.javarush.island.kotovych.settings.Settings;
import com.javarush.island.kotovych.util.EmojiTable;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.*;

@Getter
@Setter
public class Square {
    private final int x;

    private final int y;

    private Map<String, Integer> organismCount = new HashMap<>();
    private CopyOnWriteArrayList<Organism> organismList = new CopyOnWriteArrayList<>();

    private Semaphore semaphore = new Semaphore(1);

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        fill();
    }

    public void addOrganism(Organism organism) {
        try {
            semaphore.acquire();
            if (this.getOrganismList().size() < Settings.getMaxAnimalsOnOneSquare()
                    && getOrganismCount().get(organism.getName()) == null
                    || getOrganismCount().get(organism.getName()) < organism.getMaxOnOneSquare()){
                organismList.add(organism);
                organismCount.put(organism.getName(), organismCount.getOrDefault(organism.getName(), 0) + 1);
            } else{
                throw new AppException("Too many animals");
            }
        } catch (Exception e) {
            throw new AppException(e);
        } finally {
            semaphore.release();
        }
    }

    public void removeOrganism(Organism organism) {
        try {
            semaphore.acquire();
            organismList.remove(organism);
            organismCount.put(organism.getName(), organismCount.get(organism.getName()) - 1);
        } catch (Exception e) {
            throw new AppException(e);
        } finally {
            semaphore.release();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("""
                Square at (%d, %d) - Entities: %d {
                """.formatted(this.getX(), this.getY(), this.getOrganismList().size()));
        for (Map.Entry<String, Integer> entry : organismCount.entrySet()) {
            builder.append("\t%s: %d\n".formatted(EmojiTable.getEmoji(entry.getKey()), entry.getValue()));
        }
        builder.append("}");
        return builder.toString();
    }

    private void fill() {
        Object[] organisms = OrganismFactory.getOrganismPrototypes().keySet().toArray();
        while (organismList.size() != Settings.getAnimalsOnSquareAtTheBeginning()){
            try {
                Organism organism = OrganismFactory.newOrganism((String) organisms[ThreadLocalRandom.current().nextInt(organisms.length)]);
                addOrganism(organism);
            } catch (AppException e){

            }
        }
    }
}
