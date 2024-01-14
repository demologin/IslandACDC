package com.javarush.island.motyrev.entities.animals;

import com.javarush.island.motyrev.api.Parameters;
import com.javarush.island.motyrev.entities.Entity;
import com.javarush.island.motyrev.entities.posibilities.Eatable;
import com.javarush.island.motyrev.entities.posibilities.Movable;
import com.javarush.island.motyrev.field.Cell;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Entity implements Movable, Eatable {
    private final int SEX;
    private final int SPEED;
    private final double LOSE_WEIGHT;
    private double canEatForOneTime;
    private boolean readyMove = true;
    private final Map<Class<? extends Entity>, Integer> eatProbability;

    public Animal(int sex, double weight, int speed, double entityNeededFood, double loseWeight,
                  Map<Class<? extends Entity>, Integer> eatProbability)
    {
        super(weight);
        this.SEX = sex;
        this.SPEED = speed;
        this.canEatForOneTime = entityNeededFood;
        this.LOSE_WEIGHT = loseWeight;
        this.eatProbability = eatProbability;
    }

    @Override
    public List<Entity> eat(List<Entity> entityInCell) {
        List<Entity> dieEntity = new LinkedList<>();

        double maxWeight = Parameters.ENTITY_WEIGHT.get(this.getClass());
        if (!this.isDead() && this.getWeight() < maxWeight && this.canEatForOneTime > 0) {
            for (Entity entity : entityInCell) {
                if (this.getWeight() >= maxWeight) {
                    break;
                }
                if (this != entity && !entity.isDead()) {
                    int randomValue = random(0, 100);
                    var chanceEatThisEntity = eatProbability.get(entity.getClass());
                    if (chanceEatThisEntity != null && chanceEatThisEntity > randomValue) {
                        double plusWeight = Math.min(entity.getWeight(), this.canEatForOneTime);
                        this.canEatForOneTime -= plusWeight;
                        double finalWeight = Math.min((this.getWeight() + plusWeight), maxWeight);
                        this.setWeight(finalWeight);
                        entity.setDead(true);
                        dieEntity.add(entity);
                    }
                }
            }
        }

        return dieEntity.isEmpty() ? null : dieEntity;
    }

    @Override
    public boolean move(int y, int x, Cell[][] field, Map<Class<? extends Entity>, Integer> amountEntities) {
        Class<? extends Entity> clazz = this.getClass();
        int newX = x;
        int newY = y;

        if (!this.readyMove){
            return false;
        }

        for (int i = 0; i < this.SPEED; i++) {

            this.setWeight(this.getWeight() - this.LOSE_WEIGHT);

            if (this.getWeight() <= 0){
                amountEntities.put(clazz, amountEntities.get(clazz) - 1);
                return true;
            }

            int chooseAxis = random(0, 2);
            int newCoordinate = random(-1, 2);
            if (chooseAxis == 0) {
                if (newY + newCoordinate > 0 && newY + newCoordinate < field.length) {
                    newY += newCoordinate;
                }
            } else {
                if (newX + newCoordinate > 0 && newX + newCoordinate < field[y].length) {
                    newX += newCoordinate;
                }
            }

            if (newX != x || newY != y){
                Map<Class<? extends Entity>, Integer> newCellAmountEntities = field[newY][newX].getAmountEntitiesInCell();
                if (newCellAmountEntities.get(clazz) < Parameters.LIMIT_ENTITIES_IN_CELL.get(clazz)) {
                    amountEntities.put(clazz, amountEntities.get(clazz) - 1);
                    field[newY][newX].getEntityInCell().add(this);
                    newCellAmountEntities.put(clazz, newCellAmountEntities.get(clazz) + 1);
                    this.readyMove = false;
                    return true;
                }
            }
        }

        this.readyMove = false;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return SEX == animal.SEX && SPEED == animal.SPEED
                && Double.compare(canEatForOneTime, animal.canEatForOneTime) == 0
                && Double.compare(LOSE_WEIGHT, animal.LOSE_WEIGHT) == 0
                && readyMove == animal.readyMove
                && Objects.equals(eatProbability, animal.eatProbability);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SEX, SPEED, canEatForOneTime, LOSE_WEIGHT, readyMove, eatProbability);
    }

    public void setCanEatForOneTime(double canEatForOneTime) {
        this.canEatForOneTime = canEatForOneTime;
    }

    public void setReadyMove(boolean readyMove) {
        this.readyMove = readyMove;
    }

    private int random(int start, int end) {
        return ThreadLocalRandom.current().nextInt(start, end);
    }
}
