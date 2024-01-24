package com.javarush.island.khasanov.repository;

import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.entity.Position;
import com.javarush.island.khasanov.exception.SimulationException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class IslandObjectCreator {
    private IslandObjectCreator() {}

    public static IslandObject nextIslandObject(Prototype prototype, Island island, Position position) {
        Class<? extends IslandObject> islandObjectClass = prototype.islandObject.getClass();
        Constructor<? extends IslandObject> constructor;
        try {
            constructor = islandObjectClass.getConstructor(Island.class, Position.class);
            return constructor.newInstance(island, position);
        } catch (NoSuchMethodException | InvocationTargetException
                 | InstantiationException | IllegalAccessException e) {
            throw new SimulationException(e);
        }
    }
}
