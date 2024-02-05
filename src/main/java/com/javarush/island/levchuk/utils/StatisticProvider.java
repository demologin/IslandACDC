package com.javarush.island.levchuk.utils;

import com.javarush.island.levchuk.IslandMap.Area;
import com.javarush.island.levchuk.IslandMap.Location;
import com.javarush.island.levchuk.entity.Entity;


import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StatisticProvider {
    private final ConsoleProvider consoleProvider;

    public StatisticProvider(ConsoleProvider consoleProvider) {
        this.consoleProvider = consoleProvider;
    }

    public void printIslandArea(Area area) {
        Map<String, Integer> map = new TreeMap<>();
        Location[][] locations = area.getLocations();
        for (Location[] locationXES : locations) {
            for (Location location : locationXES) {
                Map<Class<? extends Entity>, List<Entity>> organisms = location.getEntities();
                for (Map.Entry<Class<? extends Entity>, List<Entity>> entry : organisms.entrySet()) {
                    String key = entry.getKey().getSimpleName();
                    int value = entry.getValue().size();
                    if (!map.containsKey(key)) {
                        map.put(key, value);
                    } else {
                        map.put(key, map.get(key) + value);
                    }
                }

            }

        }
        consoleProvider.println("*** Area statistic ***");
        for (Map.Entry<String, Integer> pair : map.entrySet()) {
            consoleProvider.println(pair.getKey() + " " + pair.getValue());
        }
    }

    public void printByCell(Area area) {
        Map<String, StringBuilder> mapSb = new TreeMap<>();
        Map<Class<? extends Entity>, Entity> map = EntityFactory.getEntities();
        mapSb.put("!loc", new StringBuilder());
        for (Map.Entry<Class<? extends Entity>, Entity> item : map.entrySet()) {
            mapSb.put(item.getValue().getIcon(), new StringBuilder());
        }
        Location[][] locations = area.getLocations();
        for (int j = 0; j < locations[0].length; j++) {
            for (int i = 0; i < locations.length; i++) {
                Map<Class<? extends Entity>, List<Entity>> organisms = locations[i][j].getEntities();
                if (!mapSb.containsKey("!loc")) {
                    mapSb.put("!loc", new StringBuilder("|- " + i + "," + j + " -|"));
                } else {
                    mapSb.put("!loc", mapSb.get("!loc").append(" ").append("|- ").append(i).append(",").append(j).append(" -|"));
                }
                int length = mapSb.get("!loc").length();
                for (Map.Entry<Class<? extends Entity>, List<Entity>> organism : organisms.entrySet()) {
                    String key;
                    if (!organism.getValue().isEmpty()) {
                        try {
                            key = (String) organism.getKey().getMethod("getIcon").invoke(organism.getValue().get(0));
                        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                            throw new RuntimeException(e);
                        }
                        int value = organism.getValue().size();
                        mapSb.put(key, mapSb.get(key).append(" ").append(key).append(" ").append(value));
                    }
                }
                for (Map.Entry<String, StringBuilder> map2 : mapSb.entrySet()) {
                    while (map2.getValue().length() < length) {
                        map2.getValue().append(" ");
                    }
                }
            }
            mapSb.forEach((k, v) -> consoleProvider.println(v));
            for (Map.Entry<String, StringBuilder> map2 : mapSb.entrySet()) {
                map2.getValue().setLength(0);
            }
        }
    }
}
