package com.javarush.island.berezovskiy.Entities.Organism.Plants;


import com.javarush.island.berezovskiy.Constants.Constants;
import com.javarush.island.berezovskiy.Entities.Organism.Reproducible;
import com.javarush.island.berezovskiy.Entities.Organism.Organism;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Plant extends Organism implements Reproducible {

    @Override
    public String giveNameOfNewOrganism() {
        return Constants.UNNAMED;
    }
}
