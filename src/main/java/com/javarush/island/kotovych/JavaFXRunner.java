package com.javarush.island.kotovych;

import com.javarush.island.kotovych.game.visual.VisualGameScene;
import com.javarush.island.kotovych.game.GameScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class JavaFXRunner extends Application {
    GameScene gameScene = new GameScene();

    public static void main(String[] args) throws InterruptedException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VisualGameScene matrixPane = new VisualGameScene(gameScene);
        BorderPane root = new BorderPane();
        root.setCenter(matrixPane.createScrollPane(matrixPane.createMatrixPane()));
        root.setBottom(matrixPane.createControlPanel());

        Scene scene = new Scene(root, 400, 400);

        primaryStage.setTitle("Island simulation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
