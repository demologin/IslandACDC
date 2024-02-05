package com.javarush.island.alimova.entity.map;

import com.javarush.island.alimova.configure.SettingsEntity;
import com.javarush.island.alimova.entity.alive.Organism;
import lombok.Getter;

import java.util.*;
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
        listAmountOrganism = new long[settings.nameOrganism.length];
    }

    private final Map<Class<?>, List<Organism>> manyCreatures = new HashMap<>();
    private final Queue<Organism> additionQueue = new ArrayDeque<>();

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
        this.additionQueue.add(organism);
        String name = organism.getClass().getSimpleName();
        int indexOrganism = settings.getIndexOrganism(name);
        listAmountOrganism[indexOrganism] += 1;
        statisticOrganism.addNewOrganism(indexOrganism);
    }

    public void addOrganismToQueueWithoutStatistic(Organism organism) {
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
        int index = settings.getIndexOrganism(name);
        if (listAmountOrganism[index] != 0) {
            listAmountOrganism[index]--;
        }
        statisticOrganism.deleteOrganism(index);
    }

    public boolean deleteOrganism(Organism organism) {
        List<Organism> organismList = manyCreatures.get(organism.getClass());
        boolean deleteResult = organismList.remove(organism);
        if(deleteResult) {
            deleteOrganismFromStatistics(organism.getClass().getSimpleName());
        }
        return deleteResult;
    }

    public void killOrganism(Organism organism) {
        this.murderQueue.add(organism);
    }

    public void recordDeath(String name) {
        int indexOrganism = settings.getIndexOrganism(name);
        statisticOrganism.recordDeathOrganism(indexOrganism);
    }


    public void addOrganismsFromQueue() {
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
                    String name = organism.getClass().getSimpleName();
                    deleteOrganismFromStatistics(name);
                    recordDeath(name);
                }

            }
        }
    }

    public Set<Class<?>> getSetKind() {
        return manyCreatures.keySet();
    }

    public void addAnimalToMove(Organism organism) {
        TransferOrganism transferOrganism = new TransferOrganism();
        transferOrganism.setOrganism(organism);
        transferOrganism.setAddressStart(heightCoordinate, widthCoordinate);
        
        int indexOrganism = settings.getIndexOrganism(organism.getClass());
        int speed = ThreadLocalRandom.current().nextInt(0, settings.maxSpeedOrganism[indexOrganism] + 1);
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
