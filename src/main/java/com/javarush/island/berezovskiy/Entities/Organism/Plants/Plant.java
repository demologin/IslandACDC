package com.javarush.island.berezovskiy.Entities.Organism.Plants;


import com.javarush.island.berezovskiy.Constants.Constants;
import com.javarush.island.berezovskiy.Interfaces.Reproducible;
import com.javarush.island.berezovskiy.Entities.Organism.Organism;

public abstract class Plant extends Organism implements Reproducible {

    protected Plant(){
        super();
        organismType = Constants.PLANT;
    }

    @Override
    public String giveNameOfNewOrganism() {
        return Constants.UNNAMED;
    }
}
