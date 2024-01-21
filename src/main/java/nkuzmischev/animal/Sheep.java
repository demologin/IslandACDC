package nkuzmischev.animal;

import nkuzmischev.Island;

import java.util.List;

public class Sheep extends Animal {
    private List<Animal> animals;

    public Sheep(Island island, int x, int y) {
        super(island, x, y);
    }

    @Override
    public void run() {
    }

    @Override
    public void reproduce() {
        super.reproduce();
    }



}
