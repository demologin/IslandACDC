package nkuzmischev.animal;

import nkuzmischev.Island;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class Animal implements Runnable {
    public Island island;
    public int x, y;



    public Animal(Island island, int x, int y) {
        this.island = island;
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(this::reproduce, 0, 1, TimeUnit.SECONDS); // Вызов reproduce каждую секунду
    }

    public void reproduce() {
        if (island.isValidLocation(x, y)) {
            Animal[] neighbors = island.getNeighbors(x, y);
            int countSameType = 0;
            for (Animal neighbor : neighbors) {
                if (neighbor != null && neighbor.getClass() == this.getClass()) {
                    countSameType++;
                }
            }
            if (countSameType >= 2) {
                island.placeNewAnimal(this.getClass(), x, y);
            }
        }
    }
}

