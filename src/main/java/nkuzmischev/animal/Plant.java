package nkuzmischev.animal;

import nkuzmischev.Island;

public class Plant implements Runnable {
    private Island island;
    private int x, y;
    private int population;

    public Plant(Island island, int x, int y, int population) {
        this.island = island;
        this.x = x;
        this.y = y;
        this.population = population;
    }

    @Override
    public void run() {
        // Plant behavior logic
    }
}
