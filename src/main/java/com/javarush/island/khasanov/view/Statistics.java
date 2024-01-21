package com.javarush.island.khasanov.view;

import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.repository.Prototypes;
import com.javarush.island.khasanov.repository.Position;

import java.util.Map;
import java.util.TreeMap;

public class Statistics {
    public static final String DELIMITER = "-".repeat(60);
    public static final String HEADER = "Statistics";
    public static final String DETAILED_STATISTICS_FORMAT = "%s : %s = %d\n";
    public static final String COMPACT_STATISTICS_FORMAT = "%s = %d\n";
    private final Island island;
    private final Map<Prototypes, Integer> countIslandObjects;

    public Statistics(Island island) {
        this.island = island;
        countIslandObjects = new TreeMap<>();
    }

    public void update() {
        countIslandObjects.clear();
//        Map<Position, Map<Prototypes, Integer>> countObjectsOnFieldMap = island.getCountObjectOnField();
//        countObjectsOnFieldMap.clear();

        for (var positionSetEntry : island.getIslandMap().entrySet()) {
            Position position = positionSetEntry.getKey();
            for (IslandObject islandObject : positionSetEntry.getValue()) {
                String islandObjectName = islandObject.getClassName().toUpperCase();
                Prototypes type = Prototypes.valueOf(islandObjectName);

//                Map<Prototypes, Integer> countObjectsMap = countObjectsOnFieldMap.get(position);
//                countObjectsMap = countObjectsMap == null ? new HashMap<>() : countObjectsMap;
//                Integer countOnField = countObjectsMap.get(type);
//                countOnField = countOnField == null ? 1 : countOnField + 1;
//                countObjectsOnFieldMap.put(position, Map.of(type, countOnField));

                Integer totalCount = countIslandObjects.get(type);
                totalCount = totalCount == null ? 1 : totalCount + 1;
                countIslandObjects.put(type, totalCount);
            }
        }
    }

    public void printCompact() {
        update();
        countIslandObjects.forEach((key, value) -> System.out.printf(COMPACT_STATISTICS_FORMAT, key, value));
    }

    public void printDetailed() {
        System.out.println(DELIMITER);
        System.out.println(HEADER);
        for (var positionMapEntry : island.getCountObjectOnField().entrySet()) {
            for (var countMapEntry : positionMapEntry.getValue().entrySet()) {
                int count = countMapEntry.getValue();

                if (count > 0) {
                    Position position = positionMapEntry.getKey();
                    Prototypes islandObject = countMapEntry.getKey();

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
