package com.javarush.island.khasanov.repository;

import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.entity.Position;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public interface ActionsQueue {
    Queue<Map<IslandObject, Position>> movements = new LinkedBlockingQueue<>();
    Queue<IslandObject> reproduction = new LinkedBlockingQueue<>();
    Queue<Map<IslandObject, List<IslandObject>>> eating = new LinkedBlockingQueue<>();
    Queue<IslandObject> dying = new LinkedBlockingQueue<>();
}
