package nkuzmischev;


import nkuzmischev.animal.Animal;
import nkuzmischev.animal.Wolf;

public class Main {
    public static void main(String[] args) {
        Island island = new Island();
        island.placeInitialAnimalsAndPlants();
        island.startSimulation();
    }
}

