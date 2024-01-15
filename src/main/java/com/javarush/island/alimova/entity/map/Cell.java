package com.javarush.island.alimova.entity.map;

import com.javarush.island.alimova.configure.SettingsEntity;
import com.javarush.island.alimova.entity.alive.Organism;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class Cell {

    private final StatisticOrganism statisticOrganism;
    private final SettingsEntity settings;
    
    public final int heightCoordinate;
    public final int widthCoordinate;

    @Getter
    private final ReentrantLock locker = new ReentrantLock();

    public Cell(int height, int width, StatisticOrganism statisticOrganism, SettingsEntity settings) {

        this.statisticOrganism = statisticOrganism;
        this.settings = settings;
        this.heightCoordinate = height;
        this.widthCoordinate = width;
        listAmountOrganism = new long[settings.nameOrganism.length];    //не очень хорошо это делать в конструкторе
    }

    private final Map<Class<?>, List<Organism>> manyCreatures = new HashMap<>();
    //если требуется только вставка и удаление, то лучше взять LinkedList
    //будем использовать связку из хэш-мапа и линкед-листа (так поедание и размножение должно быть проще)
    //либо разбить организмы и растения?
    //отдавать сет для поиска нужного организма?
    //нужен безопасный для потока сет, плюс на нём надо синхронизироваться
    //скорее всего есть проблема с удалением и добавлением в list (нужно что-то потокобезопасное)
    private final ConcurrentLinkedDeque<Organism> additionQueue = new ConcurrentLinkedDeque<>();

    private final Queue<Organism> murderQueue = new ArrayDeque<>();

    @Getter
    private final long[] listAmountOrganism;

    public long checkAmountOrganism(String name) {
        return listAmountOrganism[settings.getIndexOrganism(name)];
    }

    public Set<Map.Entry<Class<?>, List<Organism>>> getEntry() {
        return manyCreatures.entrySet();
    }

    public boolean checkLimitOrganism(String name) {
        long amountOrganism = listAmountOrganism[settings.getIndexOrganism(name)];
        return amountOrganism < settings.maxAmountOrganism[settings.getIndexOrganism(name)];
    }

    public void addOrganismToQueueWithStatistic(Organism organism) {

        //тут нужна какая-то страховка по поводу количества организмов
        //сделать булевым?
        this.additionQueue.add(organism);
        String name = organism.getClass().getSimpleName();
        int indexOrganism = settings.getIndexOrganism(name);
        listAmountOrganism[indexOrganism] += 1;
        statisticOrganism.addNewOrganism(indexOrganism);
    }

    public void addOrganismToQueueWithoutStatistic(Organism organism) {
        //System.out.println("add to queue " + heightCoordinate + " " + widthCoordinate + " "+ organism.getClass().getSimpleName());
        this.additionQueue.add(organism);
    }

    public boolean reservedPlaceForOrganism(String name) {
        boolean checkLimit = checkLimitOrganism(name);
        if (checkLimit) {
            int indexOrganism = settings.getIndexOrganism(name);
            listAmountOrganism[indexOrganism] += 1;
            statisticOrganism.addNewOrganism(indexOrganism);
        }
        return checkLimit;
    }

    public void deleteOrganismFromStatistics(String name) {
        //нужно ли удалять через клетку? или просто на месте удалять
        int index = settings.getIndexOrganism(name);
        if (listAmountOrganism[index] != 0) {
            listAmountOrganism[index]--;
        }
        statisticOrganism.deleteOrganism(index);
    }

    public boolean deleteOrganism(Organism organism) {
        List<Organism> organismList = manyCreatures.get(organism.getClass());
        boolean deleteResult = organismList.remove(organism);   //вот тут надо бы синхронизироваться (наверное), ведь в этот момент могут перебирать животных
        if(deleteResult) {
            deleteOrganismFromStatistics(organism.getClass().getSimpleName());
        }
        return deleteResult;
    }

    public void killOrganism(Organism organism) {
        this.murderQueue.add(organism);
    }


    public void addOrganismsFromQueue() {
        //в этом месте возможно нужна синхронизация
        //тут оставим так, мало ли добавятся во времяперемещения
        //тут можно доставать животных, ане проходится по ним
        Organism organism;
        while ((organism = additionQueue.poll()) != null) {
            Class<?> classOrganism = organism.getClass();
            List<Organism> listOrganism;
            listOrganism = manyCreatures.computeIfAbsent(classOrganism, k -> new LinkedList<>());
            listOrganism.add(organism);
        }
    }

    public void killOrganismFromQueue() {
        Organism organism;
        while((organism = murderQueue.poll()) != null) {
            List<Organism> listOrganism = manyCreatures.get(organism.getClass());
            if (Objects.nonNull(listOrganism)) {
                if (listOrganism.remove(organism)) {
                    deleteOrganismFromStatistics(organism.getClass().getSimpleName());
                    //System.out.println("delete " + organism.getClass().getSimpleName());
                }

            }
        }
    }

    public Set<Class<?>> getSetKind() {
        //не самое безопасное решение
        return manyCreatures.keySet();
    }

    public void addAnimalToMove(Organism organism) {
        TransferOrganism transferOrganism = new TransferOrganism();
        transferOrganism.setOrganism(organism);
        transferOrganism.setAddressStart(heightCoordinate, widthCoordinate);
        
        int indexOrganism = settings.getIndexOrganism(organism.getClass());
        int speed = ThreadLocalRandom.current().nextInt(0, settings.maxSpeedOrganism[indexOrganism]);
        if(speed == 0) {
            return;
        }

        int[] endCoordinate = getNewCoordinate(speed);
        transferOrganism.setAddressEnd(endCoordinate[0], endCoordinate[1]);

        Table.addAnimalToTransfer(transferOrganism);
    }

    private int[] getNewCoordinate(int speed) {

        int[] coordinate = new int[2];
        coordinate[0] = heightCoordinate;
        coordinate[1] = widthCoordinate;

        coordinate[0] += speed * ThreadLocalRandom.current().nextInt(-1, 2);
        coordinate[1] += speed * ThreadLocalRandom.current().nextInt(-1, 2);

        if (coordinate[0] < 0) {
            coordinate[0] += speed * 2;
        }
        if (coordinate[1] < 0) {
            coordinate[1] += speed * 2;
        }
        return coordinate;
    }

    public List<Organism> getListOrganism(Class<?> classOrganism) {
        return manyCreatures.get(classOrganism);
    }
}
