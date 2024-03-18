package com.javarush.island.boyarinov.service;

import com.javarush.island.boyarinov.entities.map.Cell;
import com.javarush.island.boyarinov.entities.map.Island;
import com.javarush.island.boyarinov.entities.organism.Organisms;
import com.javarush.island.boyarinov.entities.prototype.Prototypes;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StatisticService {

    private final Island island;
    private final Map<String, Integer> statistic = new HashMap<>();

    public StatisticService(Island island) {
        this.island = island;
    }

    public void updateStatistic() {
        cleanAndFillStatisticMap();
        Cell[][] map = island.getMap();
        for (Cell[] row : map) {
            for (Cell cell : row) {
                cell.getLock().lock();
                try {
                    Set<Organisms> organismsSet = cell.getOrganismsSet();
                    organismsSet.forEach(organism -> {
                        String organismName = organism.getName();
                        int count = statistic.get(organismName);
                        statistic.put(organismName, ++count);
                    });
                } finally {
                    cell.getLock().unlock();
                }
            }
        }
    }

    private void cleanAndFillStatisticMap() {
        Map<Class<? extends Organisms>, Organisms> prototype = Prototypes.getPrototype();
        prototype.keySet().forEach(organismClass -> statistic.put(organismClass.getSimpleName(), 0));
    }

    public Map<String, Integer> getStatistic() {
        return statistic;
    }
}
