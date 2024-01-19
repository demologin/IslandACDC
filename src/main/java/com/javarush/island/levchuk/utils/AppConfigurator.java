package com.javarush.island.levchuk.utils;

import com.javarush.island.levchuk.Const.Constants;
import com.javarush.island.levchuk.IslandMap.Area;
import com.javarush.island.levchuk.entity.Entity;
import org.reflections.Reflections;

import static com.javarush.island.levchuk.Const.Constants.DEFAULT_ENTITIES_FOLDER;


public class AppConfigurator {
    private final EntityFactory entityFactory;
    private final ConfigLoader configLoader = new ConfigLoader();
    private final AreaInitializer areaInitializer;
    public AppConfigurator(EntityFactory entityFactory, ConsoleProvider consoleProvider) {
        this.entityFactory = entityFactory;
        this.areaInitializer = new AreaInitializer(consoleProvider);
    }

    public Area init(){
        loadEntities();
        return initializeArea();
    }
    private void loadEntities() {
        Reflections reflections = new Reflections(DEFAULT_ENTITIES_FOLDER);
        reflections.getSubTypesOf(Entity.class).stream()
                .filter(e -> e.isAnnotationPresent(Config.class))
                .map(configLoader::getObject)
                .forEach(entityFactory::registerEntity);
    }
    private Area initializeArea() {
        return areaInitializer.initializeArea();
    }

}
