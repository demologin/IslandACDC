package com.javarush.island.khasanov.repository;

import com.javarush.island.khasanov.entity.*;
import com.javarush.island.khasanov.entity.animals.herbivores.*;
import com.javarush.island.khasanov.entity.animals.predators.*;
import com.javarush.island.khasanov.entity.plants.Grass;

public enum Prototype {
    WOLF(new Wolf(), "\uD83D\uDC3A"),
    BOA(new Boa(), "\uD83D\uDC0D"),
    FOX(new Fox(), "\uD83E\uDD8A"),
    BEAR(new Bear(), "\uD83D\uDC3B"),
    EAGLE(new Eagle(), "\uD83E\uDD85"),
    HORSE(new Horse(), "\uD83D\uDC0E"),
    DEER(new Deer(), "\uD83E\uDD8C"),
    RABBIT(new Rabbit(), "\uD83D\uDC07"),
    MOUSE(new Mouse(), "\uD83D\uDC01"),
    GOAT(new Goat(), "\uD83D\uDC10"),
    SHEEP(new Sheep(), "\uD83D\uDC11"),
    HOG(new Hog(), "\uD83D\uDC17"),
    BUFFALO(new Buffalo(), "\uD83D\uDC03"),
    DUCK(new Duck(), "\uD83E\uDD86"),
    CATERPILLAR(new Caterpillar(), "\uD83D\uDC1B"),
    GRASS(new Grass(), "\uD83C\uDF3F");

    public final IslandObject islandObject;
    public final String icon;

    Prototype(IslandObject islandObject, String icon) {
        this.islandObject = islandObject;
        this.icon = icon;
    }

    public static IslandObject get(String islandObjectName) {
        Prototype found = Prototype.valueOf(islandObjectName.toUpperCase());
        return found.islandObject;
    }
}
