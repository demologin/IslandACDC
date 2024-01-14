package com.javarush.island.alimova.services;

import com.javarush.island.alimova.entity.map.Cell;
import com.javarush.island.alimova.entity.map.Table;
import com.javarush.island.alimova.entity.map.TransferOrganism;

import java.util.Objects;

public class TransferWorker implements Runnable{

    final Cell[][] table;

    public TransferWorker(Cell[][] table) {
        this.table = table;
    }

    @Override
    public void run() {
        //тут зациклить? (добавить проверку по поводу прекращения работы)
        Thread currentThread = Thread.currentThread();
        while(!currentThread.isInterrupted()) {
            TransferOrganism transferOrganism = Table.getAnimalFromTransferQueue();
            if (Objects.nonNull(transferOrganism)) {
                moveOrganism(transferOrganism);
            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    currentThread.interrupt();
                    throw new RuntimeException(e);
                }
            }

        }
    }

    private void moveOrganism(TransferOrganism transferOrganism) {
        int heightEnd = transferOrganism.heightEnd;
        int widthEnd = transferOrganism.widthEnd;
        int heightStart = transferOrganism.heightStart;
        int widthStart = transferOrganism.widthStart;

        String nameOrganism = transferOrganism.organism.getClass().getSimpleName();
        if(heightEnd < table.length && widthEnd < table[0].length) {
            Cell cellStart = table[heightStart][widthStart];
            Cell cellEnd = table[heightEnd][widthEnd];
            changeCells(transferOrganism, cellEnd, nameOrganism, cellStart, heightStart, widthStart, heightEnd, widthEnd);
        }
    }

    private static void changeCells(TransferOrganism transferOrganism, Cell cellEnd, String nameOrganism, Cell cellStart, int heightStart, int widthStart, int heightEnd, int widthEnd) {
        boolean checkLimit = false;
        cellEnd.getLocker().lock();
        try {
            checkLimit = cellEnd.reservedPlaceForOrganism(nameOrganism);
        } finally {
            cellEnd.getLocker().unlock();
        }

        if (checkLimit) {
            boolean deleteOrganism = false;
            cellStart.getLocker().lock();
            try {
                deleteOrganism = cellStart.deleteOrganism(transferOrganism.organism);
            } finally {
                cellStart.getLocker().unlock();
            }

            cellEnd.getLocker().lock();
            if (deleteOrganism) {
                try {
                    cellEnd.addOrganismToQueueWithoutStatistic(transferOrganism.organism);
                    //System.out.println("Move " + nameOrganism + "[" + heightStart + " " + widthStart + "] [" + heightEnd + " " + widthEnd + "] ");
                } finally {
                    cellEnd.getLocker().unlock();
                }
            } else {
                try {
                    cellEnd.deleteOrganismFromStatistics(nameOrganism);
                } finally {
                    cellEnd.getLocker().unlock();
                }
            }
        }
    }
}
