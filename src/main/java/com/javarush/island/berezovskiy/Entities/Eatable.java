package com.javarush.island.berezovskiy.Entities;

import com.javarush.island.berezovskiy.Entities.Organism.Organism;
import com.javarush.island.berezovskiy.Entities.Organism.OrganismsSet;

public interface Eatable {
    void eat(Organism organism);
}
