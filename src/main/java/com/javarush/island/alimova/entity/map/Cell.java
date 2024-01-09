package com.javarush.island.alimova.entity.map;

import com.javarush.island.alimova.configure.DefaultSettings;
import com.javarush.island.alimova.entity.alive.Organism;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Cell {

    private final StatisticOrganism statisticOrganism;

    public Cell(StatisticOrganism statisticOrganism) {
        this.statisticOrganism = statisticOrganism;
    }

    private final Set<Organism> manyCreatures = new HashSet<>();
    //либо разбить организмы и растения?
    //отдавать сет для поиска нужного организма?
    //нужен безопасный для потока сет, плюс на нём надо синхронизироваться
    private final ConcurrentLinkedDeque<Organism> additionQueue = new ConcurrentLinkedDeque<Organism>();

    @Getter
    private final long[] listAmountOrganism = new long[DefaultSettings.nameOrganism.length];

    public long checkAmountOrganism(String name) {
        int indexOrganism = DefaultSettings.getIndexOrganism(name);
        return listAmountOrganism[indexOrganism];
    }

    public void addOrganismToQueue(Organism organism) {

        //тут нужна какая-то страховка по поводу количества организмов
        this.additionQueue.add(organism);
        String name = organism.getClass().getSimpleName();
        int indexOrganism = DefaultSettings.getIndexOrganism(name);
        listAmountOrganism[indexOrganism] += 1;
        statisticOrganism.addNewOrganism(indexOrganism);
    }

    public void addOrganismsFromQueue() {
        //в этом месте возможно нужна синхронизация
        //тут оставим так, мало ли добавятся во времяперемещения
        this.manyCreatures.addAll(additionQueue);
        additionQueue.clear();
    }

    public Set<Organism> getSetCreatures() {
        //не самое безопасное решение
        return manyCreatures;
    }
}
