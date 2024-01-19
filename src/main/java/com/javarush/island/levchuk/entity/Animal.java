package com.javarush.island.levchuk.entity;

import com.javarush.island.levchuk.IslandMap.Area;
import com.javarush.island.levchuk.IslandMap.Location;
import com.javarush.island.levchuk.IslandMap.MoveVector;
import com.javarush.island.levchuk.LiveActions.Eating;
import com.javarush.island.levchuk.LiveActions.Movable;
import com.javarush.island.levchuk.utils.EntityFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.javarush.island.levchuk.Const.Constants.MAX_CHANCE_TO_EAT;
import static com.javarush.island.levchuk.Const.Constants.MIN_PERCENT_WEIGHT_TO_DIE;

@Getter
@Setter
public class Animal extends Entity implements Movable, Eating {
    private double weight;
    private double weightDefault;
    private double weightSaturation;
    private int speedMax;
    private Map<String, Integer> likelyFood;

    @Override
    public void eat(Location location) {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        List<String> foodTypes = likelyFood.entrySet().stream().filter(m -> m.getValue() > 0).map(Map.Entry::getKey).collect(Collectors.toList());
        foodTypes.removeIf(e -> EntityFactory.getEntityClass(e) == null);
        if (!foodTypes.isEmpty()) {
            Collections.shuffle(foodTypes);//get random food
            String food = foodTypes.get(current.nextInt(foodTypes.size()));
            Integer chanceToEat = likelyFood.get(food);
            if (chanceToEat >= current.nextInt(MAX_CHANCE_TO_EAT)) {
                Class<? extends Entity> targetFood = EntityFactory.getEntityClass(food);
                List<Entity> entityList = location.getEntities().get(targetFood);
                if (entityList != null && !entityList.isEmpty()) {
                    double saturation = 0;
                    if (entityList.get(0) instanceof Animal) {
                        saturation = ((Animal) entityList.get(0)).getWeightDefault();
                    } else {
                        saturation = weightSaturation;
                    }
                    weight = weight + saturation;
                    entityList.remove(0);
                }
            }
        }
        weight = weight - weightSaturation;
        if (weight / ((Animal) EntityFactory.getEntityClass(this.getClass())).getWeightDefault() < MIN_PERCENT_WEIGHT_TO_DIE) {
            location.removeEntity(this);
        }
    }

    @Override
    public void move(Area area, Location location) {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        Location[][] locations = area.getLocations();
        MoveVector[] directions = MoveVector.values();

        int x = location.getX();
        int y = location.getY();
        for (int i = 0; i < current.nextInt(speedMax); i++) {
            int xOffset = x + directions[current.nextInt(directions.length)].getX();
            int yOffset = y + directions[current.nextInt(directions.length)].getY();
            if (xOffset >= 0 && yOffset >= 0 && xOffset < locations.length && yOffset < locations[0].length)
            {
                List<Entity> entitiesNewLocation = locations[xOffset][yOffset].getEntities().get(this.getClass());
                if (entitiesNewLocation == null || (entitiesNewLocation.size() + 1 <= getAmountMax())) {
                    x = xOffset;
                    y = yOffset;
                }
            }
        }
        locations[x][y].addEntity(this);
        location.removeEntity(this);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "name=" + getName() +
                ", icon=" + getIcon() +
                ", amountMax=" + getAmountMax() +
                ", weight=" + getWeight() +
                ", weightDefault=" + getWeightDefault() +
                ", weightSaturation=" + getWeightSaturation() +
                ", speedMax=" + getSpeedMax() +
                ", likelyFood=" + getLikelyFood() +
                '}';
    }
}
