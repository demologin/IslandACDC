package com.javarush.island.khasanov.view;

import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.repository.Prototype;
import com.javarush.island.khasanov.entity.Position;

import java.util.Map;
import java.util.TreeMap;

public class Statistics {
    public static final String DELIMITER = "-".repeat(60);
    public static final String HEADER = "Statistics";
    public static final String DETAILED_STATISTICS_FORMAT = "%s : %s = %d\n";
    public static final String COMPACT_STATISTICS_FORMAT = "%s = %d\n";
    private final Island island;
    private final Map<Prototype, Integer> countIslandObjects;

    public Statistics(Island island) {
        this.island = island;
        countIslandObjects = new TreeMap<>();
    }

    public void update() {
        countIslandObjects.clear();
        for (var positionSetEntry : island.getIslandMap().entrySet()) {
            for (IslandObject islandObject : positionSetEntry.getValue()) {
                String islandObjectName = islandObject.getClassName().toUpperCase();
                Prototype type = Prototype.valueOf(islandObjectName);

                Integer totalCount = countIslandObjects.get(type);
                totalCount = totalCount == null ? 1 : totalCount + 1;
                countIslandObjects.put(type, totalCount);
            }
        }
    }

    public void printCompact() {
        update();
        System.out.println(DELIMITER);
        countIslandObjects.entrySet().stream().parallel()
                .sorted((o1, o2) -> o2.getValue()- o1.getValue())
                .forEachOrdered(entry->
                        System.out.printf(
                            COMPACT_STATISTICS_FORMAT,
                            entry.getKey().icon,
                            entry.getValue()
                        )
                );
        System.out.println(DELIMITER);
    }

    public void printDetailed() {
        System.out.println(DELIMITER);
        System.out.println(HEADER);
        for (var positionMapEntry : island.getCountObjectOnField().entrySet()) {
            for (var countMapEntry : positionMapEntry.getValue().entrySet()) {
                int count = countMapEntry.getValue();

                if (count > 0) {
                    Position position = positionMapEntry.getKey();
                    Prototype islandObject = countMapEntry.getKey();

                    System.out.printf(
                            DETAILED_STATISTICS_FORMAT,
                            position,
                            islandObject,
                            count
                    );
                }
            }
        }
        System.out.println(DELIMITER);
    }
}
