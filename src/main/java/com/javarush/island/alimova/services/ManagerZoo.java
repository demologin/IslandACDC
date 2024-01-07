package com.javarush.island.alimova.services;

import com.javarush.island.alimova.configure.SettingsEntity;
import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.entity.map.Table;

import java.util.concurrent.ThreadLocalRandom;

public class ManagerZoo {

    public void bootstrap() {

        StatisticOrganism statisticOrganism = new StatisticOrganism();
        Table table = new Table(statisticOrganism);
        FabricOrganism fabric = new FabricOrganism(statisticOrganism);
        //fabric.createCopyBase();
        for (String name :
                SettingsEntity.nameOrganism) {
            int randomCounter = ThreadLocalRandom.current().nextInt(0, 10);
            for (int i = 0; i < randomCounter; i++) {
                Organism organism = fabric.createNewInstanceOrganism(name);
                int indexX = ThreadLocalRandom.current().nextInt(0, 10);
                int indexY = ThreadLocalRandom.current().nextInt(0, 10);
                table.addNewOrganism(indexX, indexY, organism);
            }

            //System.out.println(organism);
        }
        table.printTable();
        fabric.printStatisticOrganism();

    }
}
