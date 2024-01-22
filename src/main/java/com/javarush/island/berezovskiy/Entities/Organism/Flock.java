package com.javarush.island.berezovskiy.Entities.Organism;

import com.javarush.island.berezovskiy.Configs.Configs;
import com.javarush.island.berezovskiy.Constants.Constants;
import com.javarush.island.berezovskiy.Entities.Cell.Cell;
import com.javarush.island.berezovskiy.Entities.Direction;
import com.javarush.island.berezovskiy.Entities.Factory.OrganismFactory;
import com.javarush.island.berezovskiy.Entities.Organism.Animals.Animal;
import com.javarush.island.berezovskiy.Interfaces.Movable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Flock implements Movable {

    private final Set<Organism> organisms = Collections.synchronizedSet(new HashSet<>());
    OrganismFactory organismFactory = new OrganismFactory();
    private final OrganismsEnum organismEnum;
    private final String organismType;
    private Organism organism;
    private final Lock flockLock = new ReentrantLock();
    private int organismCount;
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
        addNewOrganismsFromStart();
        setOrganism();
    }

    public void removeOrganism(Organism organism) {
        if (!organism.isAlive) {
            organisms.remove(organism);
        }
    }
    public String getOrganismType() {
        return organismType;
    }
    public void addNewOrganismsFromStart() {
        Organism finalOrganism;
        setMaximumCountInSet(organismFactory);
        for (int i = 0; i < organismCount; i++) {
            finalOrganism = organismFactory.createOrganism(organismEnum);
            organisms.add(finalOrganism);
        }
    }
    public void setMaximumCountInSet(OrganismFactory organismFactory) {
        organismCount = ThreadLocalRandom.current().nextInt(2, organismFactory.getMaximinCountOrganism(organismEnum));
    }

    protected void addNewOrganismChild(Organism organism) {
        if (!organism.isNotReadyToGiveBirth()) {
            String name = organism.giveNameOfNewOrganism();
            if (!name.equals(Constants.UNNAMED)) {
                Organism organismChild = organismFactory.createOrganism(OrganismsEnum.valueOf(name));
                organisms.add(organismChild);
            }
        }

    }

    public void setAbleToMove() {
        flockLock.lock();
        try{
        this.ableToMove = true;}
        finally {
            flockLock.unlock();
        }
    }

    public void disableAbleToMove() {
        flockLock.lock();
        try {
            this.ableToMove = false;
        }finally {
            flockLock.unlock();
        }
    }

    private void setOrganism() {
        flockLock.lock();
        try{
        if (organisms.stream().findAny().isPresent()) {
            organism = organisms.stream().findAny().get();
        }}
        finally {
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
                int randomStep = ThreadLocalRandom.current().nextInt(0, animal.getMaximumStep() + 1);
                for (int i = 0; i < randomStep; i++) {
                    Direction direction = Direction.values()[(ThreadLocalRandom.current().nextInt(0, 4))];
                    if (!checkRandomSide(direction, randomStep, newCoordinateX, newCoordinateY)) {
                        i--;
                        continue;
                    }
                    switch (direction) {
                        case LEFT -> newCoordinateX--;
                        case RIGHT -> newCoordinateX++;
                        case UP -> newCoordinateY--;
                        case DOWN -> newCoordinateY++;
                    }
                }
                if (newCoordinateX != cell.getCoordinateX() || newCoordinateY != cell.getCoordinateY()) {
                    this.setAbleToMove();
                    this.coordinatesToMove[0] = newCoordinateX;
                    this.coordinatesToMove[1] = newCoordinateY;
                }
            }
        }finally {
            flockLock.unlock();
        }
    }

    public int[] getCoordinatesToMove() {
        return this.coordinatesToMove;
    }

    private boolean checkRandomSide(Direction direction, int randomStep, int coordinateX, int coordinateY) {
        return switch (direction) {
            case LEFT -> (coordinateX - randomStep > 0);
            case RIGHT -> (coordinateX + randomStep < Configs.ISLAND_WIDTH);
            case UP -> (coordinateY - randomStep > 0);
            case DOWN -> (coordinateY + randomStep < Configs.ISLAND_WIDTH);
        };
    }
}
