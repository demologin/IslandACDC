package com.javarush.island.kotovych.runner;

import com.javarush.island.kotovych.scene.Square;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class JavaFXRunner extends Application {

    private static final int ROWS = 50;
    private static final int COLS = 50;
    private static final int SQUARE_SIZE = 60;
    private static final double MIN_SCALE = 0.5;
    private static final double MAX_SCALE = 2.0;

    private Pane matrixPane;
    private double scaleFactor = 1.0;

    // Границы матрицы
    private double minX, minY, maxX, maxY;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        matrixPane = createMatrixPane();
        ScrollPane scrollPane = createScrollPane(matrixPane);

        Scene scene = new Scene(scrollPane, 400, 400);

        primaryStage.setTitle("Matrix Navigator");
        primaryStage.setScene(scene);
        primaryStage.show();

        updateBounds();
    }

    private Pane createMatrixPane() {
        Pane matrixPane = new Pane();

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Square square = new Square(col, row);
                Rectangle squareVisual = new Rectangle(col * SQUARE_SIZE * 1.5, row * SQUARE_SIZE * 1.5, SQUARE_SIZE, SQUARE_SIZE);
                squareVisual.setFill(Color.GRAY);
                squareVisual.setStroke(Color.BLACK);
                matrixPane.getChildren().add(squareVisual);

                // Обработка событий мыши для отображения информации о квадрате
                Tooltip tooltip = new Tooltip();
                Tooltip.install(squareVisual, tooltip);

                squareVisual.setOnMouseEntered(event -> {
                    showSquareInfo(square, tooltip);
                    squareVisual.setFill(Color.YELLOW); // Просто для визуального эффекта
                });

                squareVisual.setOnMouseExited(event -> {
                    hideSquareInfo(tooltip);
                    squareVisual.setFill(Color.GRAY);
                });
            }
        }

        return matrixPane;
    }

    private ScrollPane createScrollPane(Pane content) {
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setPannable(true);

        scrollPane.setOnScroll((ScrollEvent event) -> {
            double delta = 1.2;

            if (event.getDeltaY() < 0) {
                scaleFactor /= delta;
                if (scaleFactor < MIN_SCALE) {
                    scaleFactor = MIN_SCALE;
                }
            } else {
                scaleFactor *= delta;
                if (scaleFactor > MAX_SCALE) {
                    scaleFactor = MAX_SCALE;
                }
            }

            content.setScaleX(scaleFactor);
            content.setScaleY(scaleFactor);

            updateBounds();

            event.consume();
        });

        return scrollPane;
    }

    // Обновление границ матрицы
    private void updateBounds() {
        minX = 0;
        minY = 0;
        maxX = COLS * SQUARE_SIZE * 1.5 * MAX_SCALE - matrixPane.getWidth() / scaleFactor;
        maxY = ROWS * SQUARE_SIZE * 1.5 * MAX_SCALE - matrixPane.getHeight() / scaleFactor;
    }

    // Показать информацию о квадрате
    private void showSquareInfo(Square square, Tooltip tooltip) {
        tooltip.setText(square.toString());
        tooltip.show(matrixPane.getScene().getWindow(), square.getX() * SQUARE_SIZE * 1.5, square.getY() * SQUARE_SIZE * 1.5);
    }

    // Скрыть информацию о квадрате
    private void hideSquareInfo(Tooltip tooltip) {
        tooltip.hide();
    }
}
