package com.javarush.island.boyarinov.entities.map;

import com.javarush.island.boyarinov.entities.organism.Organisms;

import java.util.HashSet;
import java.util.Set;

public class Cell {

    private final Set<Organisms> organismsSet = new HashSet<>();

    public Cell() {
    }

    public Set<Organisms> getOrganismsSet() {
        return organismsSet;
    }
}
