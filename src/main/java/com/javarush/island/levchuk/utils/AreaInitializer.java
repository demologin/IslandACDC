package com.javarush.island.levchuk.utils;

import com.javarush.island.levchuk.Const.Constants;
import com.javarush.island.levchuk.IslandMap.Area;
import com.javarush.island.levchuk.IslandMap.Location;
import com.javarush.island.levchuk.entity.Entity;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static com.javarush.island.levchuk.Const.Constants.MAX_LENGTH_AXIS_X;
import static com.javarush.island.levchuk.Const.Constants.MIN_LENGTH_AXIS_Y;

public class AreaInitializer {
    private final ConsoleProvider consoleProvider;

    public AreaInitializer(ConsoleProvider consoleProvider) {
        this.consoleProvider = consoleProvider;
    }

    public Area initializeArea() {
        Area area = getIslandSize();
        Location[][] locations = area.getLocations();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[0].length; j++) {
                Map<Class<? extends Entity>, Entity> organisms = EntityFactory.getEntities();
                locations[i][j] = new Location(i, j);
                for (Map.Entry<Class<? extends Entity>, Entity> entityItem : organisms.entrySet()) {
                    int amountMax = entityItem.getValue().getAmountMax();
                    int countEntitiesByType = random.nextInt(amountMax);
                    for (int k = 0; k < countEntitiesByType; k++) {
                        locations[i][j].addEntity(EntityFactory.getEntityClass(entityItem.getKey()));
                    }
                }
            }
        }
        return area;
    }

    public Area getIslandSize() {
        int x = getAreaSizeX();
        int y = getAreaSizeY();
        return new Area(x, y);
    }

    private int getAreaSizeX() {
        return getAreaDimension("X", MAX_LENGTH_AXIS_X);
    }

    private int getAreaSizeY() {
        return getAreaDimension("Y", MIN_LENGTH_AXIS_Y);
    }

    private int getAreaDimension(String dimension, int maxSizeDimension) {
        consoleProvider.print("Enter Island " + dimension + " size (1:" + maxSizeDimension + "):");
        String inputLine = consoleProvider.read();
        if (inputLine != null) {
            try {
                int size = Integer.parseInt(inputLine);
                if (size > 0 && size <= maxSizeDimension) {
                    return size;
                }
                throw new IllegalArgumentException("Invalid input size. Check input data. ");
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Input is not a number. Check input data. ");
            }
        }
        throw new IllegalArgumentException("Invalid input data. Check input data. ");
    }
}

