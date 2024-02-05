package com.javarush.island.motyrev.field;

import com.javarush.island.motyrev.api.Parameters;
import com.javarush.island.motyrev.entities.Entity;
import com.javarush.island.motyrev.entities.animals.Animal;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GameField {
    private final Cell[][] FIELD;
    private final ExecutorService service = Executors.newFixedThreadPool(4);

    public GameField(Cell[][] field) {
        this.FIELD = field;
    }

    public void revitalizeIsland() {
        while (true) {
            showIslandStatistic();
            actionAnimalsInCell();
            moveAnimalFromCellToCell();
            setStartParameters();
        }
    }

    private void showIslandStatistic() {
        Map<Class<? extends Entity>, Integer> amountEntitiesInField = initStatisticField();

        for (Cell[] cells : FIELD) {
            for (Cell cell : cells) {
                Map<Class<? extends Entity>, Integer> amountEntitiesInCell = cell.getAmountEntitiesInCell();
                for (Map.Entry<Class<? extends Entity>, Integer> entry : amountEntitiesInCell.entrySet()) {
                    Class<? extends Entity> clazz = entry.getKey();
                    int oldAmount = amountEntitiesInField.get(clazz);
                    int newAmount = oldAmount + entry.getValue();
                    amountEntitiesInField.put(clazz, newAmount);
                }
            }
        }

        displayStatistic(amountEntitiesInField);
    }

    private void actionAnimalsInCell() {
        List<Future<?>> list = new ArrayList<>();

        for (Cell[] cells : FIELD) {
            for (Cell cell : cells) {
                list.add(service.submit(cell));
            }
        }
        for (Future<?> future : list) {
            while (!future.isDone()){
            }
        }
    }

    private void moveAnimalFromCellToCell() {
        boolean isMove = false;
        for (int i = 0; i < FIELD.length; i++) {
            for (int j = 0; j < FIELD[i].length; j++) {
                List<Entity> listEntities = FIELD[i][j].getEntityInCell();
                Map<Class<? extends Entity>, Integer> amountEntitiesInCell = FIELD[i][j].getAmountEntitiesInCell();
                Iterator<Entity> iterator = listEntities.iterator();
                while (iterator.hasNext()){
                    Entity entity = iterator.next();
                    if (entity.getClass().getSuperclass() == Animal.class) {
                        Animal animal = (Animal) entity;
                        isMove = animal.move(i, j, FIELD, amountEntitiesInCell);
                    }
                    if (isMove){
                        iterator.remove();
                    }
                }
            }
        }
    }

    private void setStartParameters() {

        for (Cell[] cells : FIELD) {
            for (Cell cell : cells) {
                List<Entity> listEntity = cell.getEntityInCell();
                for (Entity entity : listEntity) {
                    if (entity.getClass().getSuperclass() == Animal.class) {
                        Animal animal = (Animal) entity;
                        animal.setCanEatForOneTime(Parameters.ENTITY_CAN_EAT_FOR_ONE_TIME.get(entity.getClass()));
                        animal.setReadyMove(true);
                    }
                    entity.setReadyMultiply(true);
                }
            }
        }
    }

    private void displayStatistic(Map<Class<? extends Entity>, Integer> amountEntitiesInField) {
        StringBuilder sb = new StringBuilder();
        sb.append("Alive animal on the field day ");
        sb.append(++Parameters.counter);
        sb.append("\n");
        sb.append("\n");
        for (Map.Entry<Class<? extends Entity>, Integer> entry : amountEntitiesInField.entrySet()) {
            sb.append(Parameters.DISPLAY_ENTITIES.get(entry.getKey()));
            sb.append(" = ");
            sb.append(entry.getValue());
            sb.append(" | ");
        }
        sb.append("\n");
        System.out.println(sb);
    }

    private Map<Class<? extends Entity>, Integer> initStatisticField() {
        Map<Class<? extends Entity>, Integer> amountEntitiesInField = new HashMap<>();
        for (Map.Entry<Class<? extends Entity>, Integer> startEntries : Parameters.LIMIT_ENTITIES_IN_CELL.entrySet()) {
            amountEntitiesInField.put(startEntries.getKey(), 0);
        }
        return amountEntitiesInField;
    }

}
