package com.javarush.island.khasanov.entity.animals;

import com.javarush.island.khasanov.config.Setting;
import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.entity.IslandObject;
import com.javarush.island.khasanov.entity.Position;
import com.javarush.island.khasanov.repository.*;
import com.javarush.island.khasanov.util.Rndm;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Setter
@Getter
public class Animal extends IslandObject {
    private int maxStepsPerTurn;
    private double necessaryForSaturation;
    private int starvePerTurn;
    private Map<String, Integer> foodMap;


    protected Animal(Island island, Position position) {
        super(island, position);
        Setting.loadIslandObject(this);
    }

    protected Animal() {
        super();
    }

    protected Animal(Animal animal) {
        super(animal.island, animal.position);
        maxStepsPerTurn = animal.getMaxStepsPerTurn();
        necessaryForSaturation = animal.getNecessaryForSaturation();
        starvePerTurn = animal.getStarvePerTurn();
        foodMap = new HashMap<>(animal.getFoodMap());
    }

    public List<IslandObject> eat() {
        List<IslandObject> foodList = new ArrayList<>();

        Set<IslandObject> nearbyObjects = island.getIslandMap().get(position);
        for (IslandObject nearbyObject : nearbyObjects) {
            String foodName = nearbyObject.getClassName();
            Integer probability = foodMap.get(foodName);

            if (nearbyObject.isHereAndIsAlive() && probability != null) {
                int percent = Rndm.nextPercent();
                if (probability >= percent) {
                    foodList.add(nearbyObject);
                    nearbyObject.die();
                }
            }
        }

        return foodList;
    }

    public Position move() {
        if (isHereAndIsAlive()) {
            int limitX = Setting.width - 1;
            int limitY = Setting.height - 1;

            int x = Rndm.nextSteps(maxStepsPerTurn, limitX);
            int y = Rndm.nextSteps(maxStepsPerTurn-x, limitY);

            int forwardX = adjustStep(position.getX() + x, limitX);
            int forwardY = adjustStep(position.getY() + y, limitY);
            int backX = adjustStep(position.getX() - x, limitX);
            int backY = adjustStep(position.getY() - y, limitY);

            x = Rndm.choose(forwardX, backX);
            y = Rndm.choose(forwardY, backY);

            return island.getPositions()[x][y];
        }
        return position;
    }

    private int adjustStep(int step, int limit) {
        int result = Math.min(step, limit);
        return step < 0 ? 0 : result;
    }

    public IslandObject reproduce() {
        boolean readyForReproduce = getReadyForReproduce().get();
        boolean hereAndIsAlive = isHereAndIsAlive();

        if (readyForReproduce && hereAndIsAlive) {
            Set<IslandObject> nearbyAnimals = new HashSet<>(island.getIslandMap().get(position));
            nearbyAnimals.remove(this);
            nearbyAnimals.removeIf(islandObject -> !islandObject.isHereAndIsAlive());

            for (IslandObject animal : nearbyAnimals) {
                if (Objects.equals(animal.getClassName(), this.getClassName())) {
                    IslandObject prepared = Prototype.get(getClassName());

                    animal.setReadyForReproduce(new AtomicBoolean(false));
                    setReadyForReproduce(new AtomicBoolean(false));

                    return prepared.copyOf(animal);
                }
            }
        }

        return this;
    }

    public void starve() {
        AtomicInteger newHp = calculateHpChanging(-starvePerTurn);
        setHp(newHp);
        if (getHp().get() == 0) {
            die();
        }
    }

    @Override
    public void saturate(double weight) {
        double percentSaturation = weight * 100d / necessaryForSaturation;
        AtomicInteger newHp = calculateHpChanging((int) percentSaturation);
        setHp(newHp);
    }

    protected void addFood(Map<String, Integer> food) {
        if (this.foodMap == null) {
            setFoodMap(food);
        } else {
            this.foodMap.putAll(food);
        }
    }

    public IslandObject copyOf(IslandObject object) {
        return new Animal((Animal) object);
    }
}
