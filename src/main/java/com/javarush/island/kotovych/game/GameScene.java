package com.javarush.island.kotovych.game;

import com.javarush.island.kotovych.controllers.*;
import com.javarush.island.kotovych.exceptions.AppException;
import com.javarush.island.kotovych.game.statistics.Statistics;
import com.javarush.island.kotovych.settings.Settings;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


@Getter
@Setter
public class GameScene{
    private Square[][] field;
    private CopyOnWriteArrayList<Square> squares = new CopyOnWriteArrayList<>();

    private ExecutorService squarePool;
    private ScheduledExecutorService controllers = Executors.newScheduledThreadPool(5);

    private int width;
    private int height;

    private Controller eatingController;
    private Controller movingController;
    private Controller reproductionController;
    private Controller dyingController;
    private Controller plantGrowingController;
    private Controller randomAnimalGeneratorController;

    private AtomicInteger totalAnimalsInGame = new AtomicInteger(0);

    private Statistics statistics = new Statistics();

    public GameScene() {
        this.width = Settings.getGameWidth();
        this.height = Settings.getGameHeight();
        squarePool = Executors.newFixedThreadPool(width * height);
        field = new Square[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                field[x][y] = new Square(x, y, statistics);
                squares.add(field[x][y]);
            }
        }

        eatingController = new EatingController(this);
        movingController = new MovingController(this);
        reproductionController = new ReproductionController(this);
        dyingController = new DeathController(this);
        plantGrowingController = new PlantGrowingController(this);
        randomAnimalGeneratorController = new RandomAnimalGeneratorController(this);

    }

    public Square getSquareByCoordinates(int x, int y) {
        return field[x][y];
    }


    public void startAllRequiredControllers(){
        int delay = Settings.getDelay();
        int animalGeneratingDelay = Settings.getAnimalGeneratingDelay();
        controllers = Executors.newScheduledThreadPool(5);
        controllers.scheduleWithFixedDelay(eatingController, 0, delay, TimeUnit.MILLISECONDS);
        controllers.scheduleWithFixedDelay(movingController, 0, delay, TimeUnit.MILLISECONDS);
        controllers.scheduleWithFixedDelay(reproductionController, 0, delay, TimeUnit.MILLISECONDS);
        controllers.scheduleWithFixedDelay(dyingController, 0, delay, TimeUnit.MILLISECONDS);
        controllers.scheduleWithFixedDelay(plantGrowingController, 0, delay, TimeUnit.MILLISECONDS);
        controllers.scheduleWithFixedDelay(randomAnimalGeneratorController, 0, animalGeneratingDelay, TimeUnit.MILLISECONDS);
    }

    public void stopControllers(){
        try {
            controllers.shutdown();
            if (!controllers.awaitTermination(2, TimeUnit.SECONDS)) {
                controllers.shutdownNow();
            }
        } catch (InterruptedException e){
            throw new AppException(e);
        }
    }
}
