package com.javarush.island.zhukov.cellTasking;

import com.javarush.island.zhukov.constans.Constants;
import com.javarush.island.zhukov.gameWorld.SetOrganism;
import com.javarush.island.zhukov.organizm.Animal;
import com.javarush.island.zhukov.organizm.Organism;
import com.javarush.island.zhukov.organizm.Plants;
import com.javarush.island.zhukov.organizm.herbivores.Caterpillar;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class AnimalsMoving {
    public void move(int x, int y) {
        synchronized (SetOrganism.class) {
            Iterator<Organism> iterator = SetOrganism.animalsInCells[x][y].iterator();
            while (iterator.hasNext()) {
                Organism organism = iterator.next();
                if (organism instanceof Plants || organism instanceof Caterpillar) {
                    continue;
                }
                Animal animal = (Animal) organism;
                int speed = animal.getSpeed();
                while (speed > 0) {
                    switch (ThreadLocalRandom.current().nextInt(0, 2)) {
                        case 0 -> movingY(organism, x, y);
                        case 1 -> movingX(organism, x, y);
                    }
                    speed--;
                }
                iterator.remove();
            }
        }
    }

    private void movingX(Organism organism, int startX, int startY) {
        int currentX = organism.getX();
        int currentY = organism.getY();
        if (currentX == 0) {
            if (startX == (currentX + 1) && startY == currentY) {
                movingY(organism, startX, startY);
                return;
            }
            organism.setX(currentX + 1);
        } else if (currentX == Constants.WIDTH_GAME - 1) {
            if (startX == currentX - 1 && startY == currentY) {
                movingY(organism, startX, startY);
                return;
            }
            organism.setX(currentX - 1);
        } else {
            randomStepX(organism, startX, startY, currentX, currentY);
        }
        removeAndAddOtherCell(organism, startX, startY, currentX, currentY);
    }

    private static void randomStepX(Organism organism, int startX, int startY, int currentX, int currentY) {
        switch (ThreadLocalRandom.current().nextInt(0, 2)) {
            case 0 -> {
                if (startX == currentX + 1 && startY == currentY) {
                    organism.setX(currentX - 1);
                } else {
                    organism.setX(currentX + 1);
                }
            }
            case 1 -> {
                if (startX == currentX - 1 && startY == currentY) {
                    organism.setX(currentX + 1);
                } else {
                    organism.setX(currentX - 1);
                }
            }
        }
    }

    private void movingY(Organism organism, int startX, int startY) {
        int currentY = organism.getY();
        int currentX = organism.getX();
        if (currentY == 0) {
            if (startY == currentY + 1 && startX == currentX) {
                movingX(organism, startX, startY);
                return;
            }
            organism.setY(currentY + 1);
        } else if (currentY == Constants.HEIGHT_GAME - 1) {
            if (startY == currentY - 1 && startX == currentX) {
                movingX(organism, startX, startY);
                return;
            }
            organism.setY(currentY - 1);
        } else {
            randomStepY(organism, startX, startY, currentY, currentX);
        }
        removeAndAddOtherCell(organism, startX, startY, currentX, currentY);
    }

    private static void randomStepY(Organism organism, int startX, int startY, int currentY, int currentX) {
        switch (ThreadLocalRandom.current().nextInt(0, 2)) {
            case 0 -> {
                if (startY == currentY + 1 && startX == currentX) {
                    organism.setY(currentY - 1);
                } else {
                    organism.setY(currentY + 1);
                }
            }
            case 1 -> {
                if (startY == currentY - 1 && startX == currentX) {
                    organism.setY(currentY + 1);
                } else {
                    organism.setY(currentY - 1);
                }
            }
        }
    }

    private static void removeAndAddOtherCell(Organism organism, int startX, int startY, int currentX, int currentY) {
        if (!(startX == currentX && startY == currentY)) {
            SetOrganism.animalsInCells[currentX][currentY].remove(organism);
        }
        SetOrganism.animalsInCells[organism.getX()][organism.getY()].add(organism);
    }
}
