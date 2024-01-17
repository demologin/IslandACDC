package com.javarush.island.khasanov.util;

import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.repository.Position;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public interface Copy {
    static Map<Position, Set<IslandObject>> deepCopyMap(Map<Position, Set<IslandObject>> src) {
        Map<Position, Set<IslandObject>> result = new TreeMap<>();

        for (var entry : src.entrySet()) {
            Position key = entry.getKey();
            Set<IslandObject> value = entry.getValue();

            Position copyPosition = new Position(key);
            Set<IslandObject> copySet = new HashSet<>(value);

            result.put(copyPosition, copySet);
        }

        return result;
    }

}
