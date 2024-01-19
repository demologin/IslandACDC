package com.javarush.island.levchuk.engine;

import com.javarush.island.levchuk.IslandMap.Area;
import com.javarush.island.levchuk.IslandMap.Location;
import com.javarush.island.levchuk.LiveActions.Eating;
import com.javarush.island.levchuk.LiveActions.Movable;
import com.javarush.island.levchuk.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class TaskHandler {

    public void eatAllInArea(Area area, ExecutorService executorService) throws InterruptedException {
        Location[][] locations = area.getLocations();
        List<Callable<Void>> tasks = new ArrayList<>();
        for (Location[] locationX : locations) {
            for (Location location : locationX) {
                Callable<Void> task = () -> {
                    Map<Class<? extends Entity>, List<Entity>> entitiesPrototype = location.getEntities();
                    for (Map.Entry<Class<? extends Entity>, List<Entity>> entitiesList : entitiesPrototype.entrySet()) {
                        List<Entity> animals = entitiesList.getValue().stream().filter(Eating.class::isInstance).collect(Collectors.toList());
                        ListIterator<Entity> iterator = animals.listIterator();
                        while (iterator.hasNext()) {
                            Eating animal = (Eating) iterator.next();
                            animal.eat(location);
                        }
                    }
                    return null;
                };
                tasks.add(task);
            }
        }
        executorService.invokeAll(tasks);
    }

    public void reproduceAllInArea(Area area, ExecutorService executorService) throws InterruptedException {
        Location[][] locations = area.getLocations();
        List<Callable<Void>> tasks = new ArrayList<>();
        for (Location[] locationX : locations) {
            for (Location location : locationX) {
                Callable<Void> task = () -> {
                    Map<Class<? extends Entity>, List<Entity>> entitiesPrototype = location.getEntities();
                    for (Map.Entry<Class<? extends Entity>, List<Entity>> entities : entitiesPrototype.entrySet()) {
                        ListIterator<Entity> iterator = entities.getValue().listIterator();
                        while (iterator.hasNext()) {
                            Entity entity = iterator.next();
                            Entity newEntity = entity.reproduce(location);
                            if (newEntity != null) {
                                iterator.add(newEntity);
                            }
                        }
                    }
                    setAllReadyForLove(entitiesPrototype);
                    return null;
                };
                tasks.add(task);
            }
        }
        executorService.invokeAll(tasks);
    }

    private void setAllReadyForLove(Map<Class<? extends Entity>, List<Entity>> entitiesPrototype) {
        entitiesPrototype.entrySet().stream().filter(b -> !b.getValue().isEmpty()).forEach(e -> e.getValue().forEach(a -> a.setReproduced(false)));
    }

    public void moveAllInArea(Area area, ExecutorService executorService) {
        Location[][] locations = area.getLocations();
        for (Location[] locationX : locations) {
            for (Location location : locationX) {
                Map<Class<? extends Entity>, List<Entity>> entitiesPrototype = location.getEntities();
                for (Map.Entry<Class<? extends Entity>, List<Entity>> entitiesList : entitiesPrototype.entrySet()) {
                    List<Entity> collect = entitiesList.getValue().stream().filter(Movable.class::isInstance).collect(Collectors.toList());
                    ListIterator<Entity> iterator = collect.listIterator();
                    while (iterator.hasNext()) {
                        ((Movable) iterator.next()).move(area, location);
                    }
                }
            }
        }
    }

}
