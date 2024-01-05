package com.javarush.island.kotovych.scene;

import com.javarush.island.kotovych.exceptions.AppException;
import com.javarush.island.kotovych.factory.OrganismFactory;
import com.javarush.island.kotovych.organisms.Organism;
import com.javarush.island.kotovych.util.EmojiTable;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Square extends Thread{
    private final int x;

    private final int y;

    private Map<String, Integer> organismCount = new HashMap<>();
    private CopyOnWriteArrayList<Organism> organismList = new CopyOnWriteArrayList<>();

    private Semaphore semaphore = new Semaphore(1);

    public Map<String, Integer> getOrganismCount(){
        organismCount = listToMap(organismList);
        return organismCount;
    }

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        fill();
    }

    @Override
    public void run() {

    }

    public void addOrganism(Organism o){
        try {
            semaphore.acquire();
            organismList.add(o);
        } catch(Exception e){
            throw new AppException(e);
        } finally {
            semaphore.release();
        }
    }

    public void removeOrganism(Organism o) {
        try {
            semaphore.acquire();
            organismList.remove(o);
        } catch (Exception e){
            throw new AppException(e);
        } finally {
            semaphore.release();
        }
    }

    @Override
    public String toString() {
        organismCount = listToMap(organismList);
        StringBuilder builder = new StringBuilder();
        builder.append("""
                        Square at (%d, %d) - Entities: %d {
                        """.formatted(this.getX(), this.getY(), this.getOrganismList().size()));
        for(Map.Entry<String, Integer> entry : organismCount.entrySet()){
            builder.append("\t%s: %d\n".formatted(EmojiTable.getEmoji(entry.getKey()), entry.getValue()));
        }
        builder.append("}");
        return builder.toString();
    }

    private void fill(){
        Object[] organisms = OrganismFactory.getOrganismPrototypes().keySet().toArray();
        for(int i = 0; i < 10; i++){
            addOrganism(OrganismFactory.newOrganism((String) organisms[ThreadLocalRandom.current().nextInt(organisms.length)]));
        }
    }

    private Map<String, Integer> listToMap(List<Organism> organisms){
        Map<String, Integer> organismMap = new HashMap<>();
        try {
            semaphore.acquire();
            for (Organism organism : organisms) {
                String name = organism.getName();
                organismMap.put(name, organismMap.getOrDefault(name, 0) + 1);
            }
        } catch (InterruptedException e){
            throw new AppException();
        } finally {
            semaphore.release();
        }

        return organismMap;
    }
}
