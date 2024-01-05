package com.javarush.island.kotovych.scene;

import com.javarush.island.kotovych.controllers.EatingController;
import com.javarush.island.kotovych.controllers.MovingController;
import com.javarush.island.kotovych.controllers.ReproductionController;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.*;


@Getter
@Setter
public class GameScene {
    private Square[][] field;
    private CopyOnWriteArrayList<Square> squares = new CopyOnWriteArrayList<>();

    private ExecutorService squarePool;
    private ScheduledExecutorService controllers = Executors.newScheduledThreadPool(3);

    private int width;
    private int height;


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

        EatingController eatingController = new EatingController(this);
        MovingController movingController = new MovingController(this);
        ReproductionController reproductionController = new ReproductionController(this);

        controllers.scheduleWithFixedDelay(eatingController, 0, eatingController.getDelay(), TimeUnit.SECONDS);
        controllers.scheduleWithFixedDelay(movingController, 0, eatingController.getDelay(), TimeUnit.SECONDS);
        controllers.scheduleWithFixedDelay(reproductionController, 0, eatingController.getDelay(), TimeUnit.SECONDS);

    }

    public Square getSquareByCoordinates(int x, int y) {
        return field[x][y];
    }
}
