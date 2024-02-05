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
        TransferOrganism transferOrganism;
        while(Objects.nonNull(transferOrganism = Table.getAnimalFromTransferQueue())) {
            moveOrganism(transferOrganism);
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
            changeCells(transferOrganism, cellEnd, nameOrganism, cellStart);
        }
    }

    private static void changeCells(TransferOrganism transferOrganism, Cell cellEnd, String nameOrganism, Cell cellStart) {
        boolean checkLimit;
        cellEnd.getLocker().lock();
        try {
            checkLimit = cellEnd.reservedPlaceForOrganism(nameOrganism);
        } finally {
            cellEnd.getLocker().unlock();
        }

        if (checkLimit) {
            boolean deleteOrganism;
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
