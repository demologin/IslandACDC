package com.javarush.island.berezovskiy.Entities.Organism;

import com.javarush.island.berezovskiy.Configs.Configs;
import com.javarush.island.berezovskiy.Constants.Constants;
import com.javarush.island.berezovskiy.Entities.Cell.Cell;
import com.javarush.island.berezovskiy.Entities.Direction;
import com.javarush.island.berezovskiy.Entities.Factory.OrganismFactory;
import com.javarush.island.berezovskiy.Entities.Organism.Animals.Animal;
import com.javarush.island.berezovskiy.Interfaces.Movable;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Flock implements Movable {

//    private int coordinateX;
//    private int coordinateY;
    private final Set<Organism> organisms = new HashSet<>();
    OrganismFactory organismFactory = new OrganismFactory();
    private final OrganismsEnum organismEnum;
    private final String organismType;
    private Organism organism;
    private int organismCount;
    private int[] coordinatesToMove;
    private boolean ableToMove = false;

    //private Cell cell;
    public int getOrganismCount() {
        return organismCount;
    }

    public Flock(OrganismsEnum organism) {
        //this.cell = cell;
        this.organismEnum = organism;
        this.organismType = organism.toString();
//        this.organismCount = maxCountInCell;
//      setCoordinates(cell.getCoordinateX(), cell.getCoordinateY());
        addNewOrganismsFromStart();
        setOrganism();
    }

    public void removeOrganism(Organism organism) {
        if(!organism.isAlive){
        organisms.remove(organism);}
    }

    public Set<Organism> getOrganismsSet() {
        return organisms;
    }

    public String getOrganismType() {
        return organismType;
    }

//    public void setCoordinates(int coordinateX, int coordinateY) {
//        this.coordinateX = coordinateX;
//        this.coordinateY = coordinateY;
//    }

    public void addNewOrganismsFromStart() {
        Organism finalOrganism;
        setMaximumCountInSet(organismFactory);
        for (int i = 0; i < organismCount; i++) {
            finalOrganism = organismFactory.createOrganism(organismEnum);
            organisms.add(finalOrganism);
        }
    }

    public void setMaximumCountInSet(OrganismFactory organismFactory){
        organismCount = ThreadLocalRandom.current().nextInt(5,organismFactory.getMaximinCountOrganism(organismEnum));
    }

    protected void addNewOrganismChild(Organism organism) {
        if(!organism.isNotReadyToGiveBirth()){
            String name = organism.giveNameOfNewOrganism();
            if(!name.equals(Constants.UNNAMED)){
                OrganismFactory organismFactory = new OrganismFactory();
                Organism organismChild = organismFactory.createOrganism(OrganismsEnum.valueOf(name));
                organisms.add(organismChild);
            }
        }

    }

    public boolean isAbleToMove(){
        return isAbleToMove();
    }
    public void disableAbleToMove(){
        this.ableToMove = false;
    }

    private void setOrganism() {
        if (organisms.stream().findAny().isPresent()) {
            organism = organisms.stream().findAny().get();
        }

    }

    public Organism getOrganism() {
        return organism;
    }

//    public int[] move() {
//        if (organism instanceof Animal animal) {
//            int randomStep = ThreadLocalRandom.current().nextInt(0, animal.getMaximumStep() + 1);
//            int newCoordinateX = coordinateX;
//            int newCoordinateY = coordinateY;
//            for (int i = 0; i < randomStep; i++) {
//                Direction direction = Direction.values()[(ThreadLocalRandom.current().nextInt(0, 4))];
//                if (!checkRandomSide(direction, randomStep, newCoordinateX, newCoordinateY)) {
//                    i--;
//                    continue;
//                }
//                switch (direction) {
//                    case LEFT -> this.coordinateX--;
//                    case RIGHT -> this.coordinateX++;
//                    case UP -> this.coordinateY--;
//                    case DOWN -> this.coordinateY++;
//                }
//            }
//        }
//        return new int[]{coordinateX, coordinateY};
//    }
    public void move(Cell cell) {
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
            if(newCoordinateX != cell.getCoordinateX() && newCoordinateY != cell.getCoordinateY()){
                this.ableToMove = true;
                this.coordinatesToMove = new int[]{newCoordinateX, newCoordinateX};
            }
        }
    }
    public int[] getCoordinatesToMove(){
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


//    public int getCoordinateX() {
//        return coordinateX;
//    }

//    public int getCoordinateY() {
//        return coordinateY;
//    }
}
