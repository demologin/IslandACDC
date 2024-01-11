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
        //while(true) {
            TransferOrganism transferOrganism = Table.getAnimalFromTransferQueue();
            if (Objects.nonNull(transferOrganism)) {
                int heightEnd = transferOrganism.heightEnd;
                int widthEnd = transferOrganism.widthEnd;
                int heightStart = transferOrganism.heightStart;;
                int widthStart = transferOrganism.widthStart;
                String nameOrganism = transferOrganism.organism.getClass().getSimpleName();
                if(heightEnd < table.length && widthEnd < table[0].length) {
                    if(table[heightEnd][widthEnd].checkLimitOrganism(nameOrganism)) {
                        table[heightEnd][widthEnd].addOrganismToQueue(transferOrganism.organism);
                        table[heightStart][widthStart].deleteOrganism(transferOrganism.organism);       //тут дополнительную проверку
                        System.out.print("\nMOVE " + nameOrganism + " [" + heightEnd + " " + widthEnd + "]");
                    }
                }
            }
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        //}


    }
}
