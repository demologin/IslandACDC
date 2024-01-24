package com.javarush.island.berezovskiy.entities.organism;

import com.javarush.island.berezovskiy.configs.Configs;
import com.javarush.island.berezovskiy.constants.Constants;
import com.javarush.island.berezovskiy.entities.cell.Cell;
import com.javarush.island.berezovskiy.entities.Direction;
import com.javarush.island.berezovskiy.factory.OrganismFactory;
import com.javarush.island.berezovskiy.entities.organism.animals.Animal;
import com.javarush.island.berezovskiy.interfaces.Movable;
import com.javarush.island.berezovskiy.utils.Rnd;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Flock implements Movable {

    private final Set<Organism> organisms = new HashSet<>();
    OrganismFactory organismFactory = new OrganismFactory();
    private final OrganismsEnum organismEnum;
    private final String organismType;
    private Organism organism;
    private final Lock flockLock = new ReentrantLock();
    private int organismCount;
    private int maximumOrganismsInFlock;

    public int getMaximumOrganismsInFlock() {
        return maximumOrganismsInFlock;
    }

    public Set<Organism> getOrganisms() {
        return organisms;
    }

    private final int[] coordinatesToMove = new int[2];

    public boolean isAbleToMove() {
        return ableToMove;
    }

    private boolean ableToMove = false;

    public int getOrganismCount() {
        return organismCount;
    }

    public Flock(OrganismsEnum organism) {
        this.organismEnum = organism;
        this.organismType = organism.toString();
    }

    public String getOrganismType() {
        return organismType;
    }

    public void addNewOrganismsFromStart() {
        flockLock.lock();
        try {
            Organism finalOrganism;
            setStartOrganismsCountInSet(organismFactory);
            for (int i = 0; i < organismCount; i++) {
                finalOrganism = organismFactory.createOrganism(organismEnum);
                organisms.add(finalOrganism);
            }
            setOrganism();
        }finally {
            flockLock.unlock();
        }
    }


    public void setStartOrganismsCountInSet(OrganismFactory organismFactory) {
        flockLock.lock();
        try {
            this.maximumOrganismsInFlock = organismFactory.getMaximinCountOrganism(organismEnum);
            organismCount = Rnd.getRandom(Configs.MIN_ANIMAL_IN_FLOCK, maximumOrganismsInFlock);
        }finally {
            flockLock.unlock();
        }
    }

    public void addNewOrganismChild(Organism organism) {
        flockLock.lock();
        try {
            if (!organism.isNotReadyToGiveBirth()) {
                String name = organism.giveNameOfNewOrganism();
                if (!name.equals(Constants.UNNAMED)) {
                    Organism organismChild = organismFactory.createOrganism(OrganismsEnum.valueOf(name));
                    organisms.add(organismChild);
                    this.organismCount++;
                }
            }
        }finally {
            flockLock.unlock();
        }
    }

    public void removeAllOrganisms(){
        flockLock.lock();
        try {
            for (Iterator<Organism> iterator = organisms.iterator(); iterator.hasNext(); ) {
                Organism organism1 = iterator.next();
                organism1.decrementOrganismCount();
                iterator.remove();
            }
        }finally {
            flockLock.unlock();
        }
    }

    public void setAbleToMove() {
        flockLock.lock();
        try {
            this.ableToMove = true;
        } finally {
            flockLock.unlock();
        }
    }

    public void disableAbleToMove() {
        flockLock.lock();
        try {
            this.ableToMove = false;
        } finally {
            flockLock.unlock();
        }
    }

    private void setOrganism() {
        flockLock.lock();
        try {
            if (organisms.stream().findAny().isPresent()) {
                organism = organisms.stream().findAny().get();
            }
        } finally {
            flockLock.unlock();
        }
    }

    public Organism getOrganism() {
        return organism;
    }

    public void move(Cell cell) {
        flockLock.lock();
        try {
            int newCoordinateX = cell.getCoordinateX();
            int newCoordinateY = cell.getCoordinateY();
            if (organism instanceof Animal animal) {
                int randomStep = Rnd.getRandom(0, animal.getMaximumStep() + 1);
                int count = 0;
                while (count < randomStep) {
                    Direction direction = Direction.values()[Rnd.getRandom(0, 4)];
                    if (!checkRandomSide(direction, randomStep, newCoordinateX, newCoordinateY)) {
                        count++;
                    } else {
                        switch (direction) {
                            case LEFT -> newCoordinateX--;
                            case RIGHT -> newCoordinateX++;
                            case UP -> newCoordinateY--;
                            case DOWN -> newCoordinateY++;
                        }
                        count++;
                    }
                }
                if (newCoordinateX != cell.getCoordinateX() || newCoordinateY != cell.getCoordinateY()) {
                    this.setAbleToMove();
                    organisms.forEach(organism1 -> organism1.setStarved(true));
                    this.coordinatesToMove[0] = newCoordinateX;
                    this.coordinatesToMove[1] = newCoordinateY;
                }
            }
        } finally {

            flockLock.unlock();
        }
    }

    public int[] getCoordinatesToMove() {
        return this.coordinatesToMove;
    }

    private boolean checkRandomSide(Direction direction, int randomStep, int coordinateX, int coordinateY) {
        flockLock.lock();
        try {
            return switch (direction) {
                case LEFT -> (coordinateX - randomStep > 0);
                case RIGHT -> (coordinateX + randomStep < Configs.ISLAND_WIDTH);
                case UP -> (coordinateY - randomStep > 0);
                case DOWN -> (coordinateY + randomStep < Configs.ISLAND_WIDTH);
            };
        } finally {
            flockLock.unlock();
        }
    }
}
