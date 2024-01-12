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
                    Thread.sleep(100);
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
            if(cellEnd.checkLimitOrganism(nameOrganism)) {
                cellEnd.reservedPlaceForOrganism(nameOrganism);                  //возможно, сделать больше проверку
                boolean delete = cellStart.deleteOrganism(transferOrganism.organism);       //тут дополнительную проверку
                if (delete) {
                System.out.print("\nMOVE " + nameOrganism + " [" + heightStart + " " + widthStart + "] -> " + " [" + heightEnd + " " + widthEnd + "]");
                    cellEnd.addOrganismToQueueWithoutStatistic(transferOrganism.organism);
                } else {
                    cellEnd.deleteOrganismFromStatistics(nameOrganism);
                }

            }
        }
    }
}
