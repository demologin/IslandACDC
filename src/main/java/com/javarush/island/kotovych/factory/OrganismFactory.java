package com.javarush.island.kotovych.factory;

import com.javarush.island.kotovych.exceptions.AppException;
import com.javarush.island.kotovych.organisms.Organism;
import com.javarush.island.kotovych.organisms.animals.carnivores.*;
import com.javarush.island.kotovych.organisms.animals.herbivores.*;
import com.javarush.island.kotovych.organisms.plants.Plant;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class OrganismFactory {
    private OrganismFactory(){}

    @Getter
    private static final Map<String, Organism> organismPrototypes = new HashMap<>(){{
        put("Bear", new Bear());
        put("Boa", new Boa());
        put("Eagle", new Eagle());
        put("Fox", new Fox());
        put("Wolf", new Wolf());
        put("Boar", new Boar());
        put("Buffalo", new Buffalo());
        put("Caterpillar", new Caterpillar());
        put("Deer", new Deer());
        put("Duck", new Duck());
        put("Goat", new Goat());
        put("Horse", new Horse());
        put("Mouse", new Mouse());
        put("Rabbit", new Rabbit());
        put("Sheep", new Sheep());
        put("Plant", new Plant());
    }};

    public static Organism newOrganism(String source){
        try {
            return organismPrototypes.get(source).clone();
        } catch (CloneNotSupportedException e){
            throw new AppException(e);
        }
    }
}
