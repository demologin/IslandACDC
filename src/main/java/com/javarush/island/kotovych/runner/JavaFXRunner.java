// JavaFXRunner.java
package com.javarush.island.kotovych.runner;

import com.javarush.island.kotovych.scene.GameScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JavaFXRunner extends Application {
    GameScene gameScene = new GameScene(50, 50);

    public static void main(String[] args) throws InterruptedException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MatrixPane matrixPane = new MatrixPane();
        Pane pane = matrixPane.createMatrixPane(gameScene);
        ScrollPane scrollPane = matrixPane.createScrollPane(pane);

        Scene scene = new Scene(scrollPane, 400, 400);

        primaryStage.setTitle("Island simulation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
