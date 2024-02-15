package com.javarush.island.zhukov.gameWorld;

import com.javarush.island.zhukov.constans.Constants;
import com.javarush.island.zhukov.organizm.Organism;

import java.util.HashSet;
import java.util.Set;

public class SetOrganism {
    public static Set<Organism>[][] animalsInCells = new HashSet[Constants.WIDTH_GAME][Constants.HEIGHT_GAME];
}
