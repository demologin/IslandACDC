package com.javarush.island.berezovskiy.entities.organism.plants;


import com.javarush.island.berezovskiy.constants.Constants;
import com.javarush.island.berezovskiy.interfaces.Reproducible;
import com.javarush.island.berezovskiy.entities.organism.Organism;

public abstract class Plant extends Organism implements Reproducible {

    protected Plant(){
        organismType = Constants.PLANT;
    }

    @Override
    public String giveNameOfNewOrganism() {
        return Constants.UNNAMED;
    }
}
