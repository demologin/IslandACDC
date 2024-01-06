package com.javarush.island.kotovych.scene;

import com.javarush.island.kotovych.controllers.*;
import com.javarush.island.kotovych.exceptions.AppException;
import com.javarush.island.kotovych.settings.Settings;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.*;


@Getter
@Setter
public class GameScene{
    private Square[][] field;
    private CopyOnWriteArrayList<Square> squares = new CopyOnWriteArrayList<>();

    private ExecutorService squarePool;
    private ScheduledExecutorService controllers = Executors.newScheduledThreadPool(5);

    private int width;
    private int height;

    private Controller eatingController = new EatingController(this);
    private Controller movingController = new MovingController(this);
    private Controller reproductionController = new ReproductionController(this);
    private Controller dyingController = new DyingController(this);
    private Controller diseaseSpreadingController = new DiseaseSpreadingController(this);
    private Controller plantGrowingController = new PlantGrowingController(this);


    public GameScene(int width, int height) {
        this.width = width;
        this.height = height;
        squarePool = Executors.newFixedThreadPool(width * height);
        field = new Square[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                field[x][y] = new Square(x, y);
                squares.add(field[x][y]);
            }
        }
    }

    public Square getSquareByCoordinates(int x, int y) {
        return field[x][y];
    }

    public void addController(Controller controller, int initialDelay, int delay, TimeUnit timeUnit){
        controllers.scheduleWithFixedDelay(controller, initialDelay, delay, timeUnit);
    }

    public void startAllRequiredControllers(){
        stopControllers();
        long delay = Settings.getDelay();
        controllers = Executors.newScheduledThreadPool(5);
        controllers.scheduleWithFixedDelay(eatingController, 0, delay, TimeUnit.MILLISECONDS);
        controllers.scheduleWithFixedDelay(movingController, 0, delay, TimeUnit.MILLISECONDS);
        controllers.scheduleWithFixedDelay(reproductionController, 0, delay, TimeUnit.MILLISECONDS);
        controllers.scheduleWithFixedDelay(dyingController, 0, delay, TimeUnit.MILLISECONDS);
        controllers.scheduleWithFixedDelay(plantGrowingController, 0, delay, TimeUnit.MILLISECONDS);
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
