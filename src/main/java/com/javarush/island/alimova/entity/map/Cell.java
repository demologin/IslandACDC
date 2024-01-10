package com.javarush.island.alimova.entity.map;

import com.javarush.island.alimova.configure.SettingsEntity;
import com.javarush.island.alimova.entity.alive.Organism;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Cell {

    private final StatisticOrganism statisticOrganism;
    private final SettingsEntity settings;

    public Cell(StatisticOrganism statisticOrganism, SettingsEntity settings) {

        this.statisticOrganism = statisticOrganism;
        this.settings = settings;
        listAmountOrganism = new long[settings.nameOrganism.length];    //не очень хорошо это делать в конструкторе
    }

    private final Map<Class<?>, LinkedList<Organism>> manyCreatures = new HashMap<>();
    //если требуется только вставка и удаление, то лучше взять LinkedList
    //будем использовать связку из хэш-мапа и линкед-листа (так поедание и размножение должно быть проще)
    //либо разбить организмы и растения?
    //отдавать сет для поиска нужного организма?
    //нужен безопасный для потока сет, плюс на нём надо синхронизироваться
    private final ConcurrentLinkedDeque<Organism> additionQueue = new ConcurrentLinkedDeque<Organism>();

    @Getter
    private final long[] listAmountOrganism;    // = new long[settings.nameOrganism.length];

    public long checkAmountOrganism(String name) {
        return listAmountOrganism[settings.getIndexOrganism(name)];
    }

    public boolean checkLimitOrganism(String name) {
        long amountOrganism = listAmountOrganism[settings.getIndexOrganism(name)];
        return amountOrganism < settings.maxAmountOrganism[settings.getIndexOrganism(name)];
    }

    public void addOrganismToQueue(Organism organism) {

        //тут нужна какая-то страховка по поводу количества организмов
        this.additionQueue.add(organism);
        String name = organism.getClass().getSimpleName();
        int indexOrganism = settings.getIndexOrganism(name);
        listAmountOrganism[indexOrganism] += 1;
        statisticOrganism.addNewOrganism(indexOrganism);
    }

    public void addOrganismsFromQueue() {
        //в этом месте возможно нужна синхронизация
        //тут оставим так, мало ли добавятся во времяперемещения
        for (Organism organism : additionQueue) {
            Class<?> classOrganism = organism.getClass();
            var listOrganism = manyCreatures.computeIfAbsent(classOrganism, k -> new LinkedList<>());
            listOrganism.add(organism);

        }
        additionQueue.clear();
    }

    public Set<Class<?>> getSetKind() {
        //не самое безопасное решение
        return manyCreatures.keySet();
    }

    public List<Organism> getListOrganism(Class<?> classOrganism) {
        return manyCreatures.get(classOrganism);
    }
}
