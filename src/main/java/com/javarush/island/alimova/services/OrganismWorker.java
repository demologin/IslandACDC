package com.javarush.island.alimova.services;

import com.javarush.island.alimova.configure.SettingsEntity;
import com.javarush.island.alimova.entity.alive.Organism;
import com.javarush.island.alimova.entity.alive.animals.Animal;
import com.javarush.island.alimova.entity.alive.plants.Plant;
import com.javarush.island.alimova.entity.map.Cell;

import java.util.List;
import java.util.Set;

public class OrganismWorker implements Runnable{

    private final Cell cell;
    private final SettingsEntity settings;

    public OrganismWorker(Cell cell, SettingsEntity settings) {
        this.cell = cell;
        this.settings = settings;
    }

    @Override
    public void run() {

        Set<Class<?>> organismSet = cell.getSetKind();
        //размножение видов
        for (Class<?> classOrganism : organismSet) {
            if (Plant.class.isAssignableFrom(classOrganism)) {
                List<Organism> listOrganism = cell.getListOrganism(classOrganism);
                for (Organism plant : listOrganism) {
                    ((Plant)plant).multiply(cell);
                }
            }

            if (Animal.class.isAssignableFrom(classOrganism)) {
                List<Organism> listOrganism = cell.getListOrganism(classOrganism);
                for (Organism animal : listOrganism) {
                    (((Animal)animal)).eat(cell, settings);
                    //todo пока не работает
                }
            }
        }
        cell.addOrganismsFromQueue();
    }
}
