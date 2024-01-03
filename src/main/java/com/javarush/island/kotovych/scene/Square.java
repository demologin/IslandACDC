package com.javarush.island.kotovych.scene;

import com.javarush.island.kotovych.exceptions.AppException;
import com.javarush.island.kotovych.factory.OrganismFactory;
import com.javarush.island.kotovych.organisms.Organism;
import com.javarush.island.kotovych.util.EmojiTable;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

@Getter
@Setter
public class Square extends Thread{
    private final int x;

    private final int y;

    private Map<String, Integer> organismCount = new HashMap<>();
    private List<Organism> organismList = new ArrayList<>();

    private Semaphore semaphore = new Semaphore(1);

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        fill();
        start();
    }

    public void addOrganism(Organism o){
        try {
            semaphore.acquire();
            organismList.add(o);
            organismCount = listToMap(organismList);
            semaphore.release();
        } catch(InterruptedException e){
            throw new AppException(e);
        }
    }

    public void removeOrganism(Organism o) {
        try {
            semaphore.acquire();
            organismList.remove(o);
            organismCount = listToMap(organismList);
            semaphore.release();
        } catch (InterruptedException e){
            throw new AppException(e);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("""
                        Square at (%d, %d) - Entities: %d {
                         
                        """.formatted(this.getX(), this.getY(), this.getOrganismCount().size()));
        for(Map.Entry<String, Integer> entry : organismCount.entrySet()){
            builder.append("\t%s: %d\n".formatted(EmojiTable.getEmoji(entry.getKey()), entry.getValue()));
        }
        builder.append("}");
        return builder.toString();
    }

    private void fill(){
        addOrganism(OrganismFactory.newOrganism("Caterpillar"));
        addOrganism(OrganismFactory.newOrganism("Bear"));
        addOrganism(OrganismFactory.newOrganism("Bear"));
        addOrganism(OrganismFactory.newOrganism("Bear"));
    }

    private Map<String, Integer> listToMap(List<Organism> organisms){
        Map<String, Integer> organismMap = new HashMap<>();

        for (Organism organism : organisms) {
            String name = organism.getName();
            organismMap.put(name, organismMap.getOrDefault(name, 0) + 1);
        }

        return organismMap;
    }
}
