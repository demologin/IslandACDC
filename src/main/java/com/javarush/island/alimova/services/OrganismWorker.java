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

        cell.getLocker().lock();
        try {
            Set<Class<?>> organismSet = cell.getSetKind();
            //размножение видов
            for (Class<?> classOrganism : organismSet) {
                boolean checkSatiety = false;
                if (Plant.class.isAssignableFrom(classOrganism)) {
                    multiplyPlant(classOrganism);
                }

                if (Animal.class.isAssignableFrom(classOrganism)) {
                    eatAnimal(classOrganism);
                    multiplyAnimal(classOrganism, checkSatiety);
                    moveAnimal(classOrganism);
                }

            }
            cell.addOrganismsFromQueue();
        } finally {
            cell.getLocker().unlock();
        }

    }

    private void moveAnimal(Class<?> classOrganism) {
        List<Organism> listOrganism = cell.getListOrganism(classOrganism);//не уверена, что верно тут синхронизировать
        for (Organism organism : listOrganism) {
            Animal animal = (Animal)organism;
            animal.move(cell);
        }

    }

    private void multiplyAnimal(Class<?> classOrganism, boolean checkSatiety) {
        List<Organism> listOrganism = cell.getListOrganism(classOrganism);
        for (Organism organism : listOrganism) {
            Animal animal = (Animal)organism;
            if(animal.isSatiety()) {
                if (checkSatiety) {
                    animal.multiply(cell);
                    checkSatiety = false;
                } else {
                    checkSatiety = true;
                }
            }
        }

    }

    private void eatAnimal(Class<?> classOrganism) {
        List<Organism> listOrganism = cell.getListOrganism(classOrganism);
        for (Organism animal : listOrganism) {
            (((Animal)animal)).eat(cell, settings);
        }

    }

    private void multiplyPlant(Class<?> classOrganism) {
        List<Organism> listOrganism = cell.getListOrganism(classOrganism);
        for (Organism plant : listOrganism) {
            plant.multiply(cell);
        }

    }
}
