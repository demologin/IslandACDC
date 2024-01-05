// MatrixPane.java
package com.javarush.island.kotovych.visual;

import com.javarush.island.kotovych.scene.GameScene;
import com.javarush.island.kotovych.scene.Square;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MatrixPane {
    private static final int SQUARE_SIZE = 60;
    private static final double MIN_SCALE = 0.2;
    private static final double MAX_SCALE = 2.0;

    private Pane matrixPane;
    private double scaleFactor = 1.0;

    public Pane createMatrixPane(GameScene gameScene) {
        int ROWS = gameScene.getHeight();
        int COLS = gameScene.getWidth();
        matrixPane = new Pane();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Square square = gameScene.getSquareByCoordinates(row, col);

                Rectangle squareVisual = new Rectangle(col * SQUARE_SIZE * 1.5, row * SQUARE_SIZE * 1.5, SQUARE_SIZE, SQUARE_SIZE);
                squareVisual.setFill(Color.GRAY);
                squareVisual.setStroke(Color.BLACK);
                matrixPane.getChildren().add(squareVisual);

                Tooltip tooltip = new Tooltip();
                Tooltip.install(squareVisual, tooltip);

                squareVisual.setOnMouseEntered(event -> {
                    showSquareInfo(square, tooltip);
                    squareVisual.setFill(Color.YELLOW);
                });

                squareVisual.setOnMouseExited(event -> {
                    hideSquareInfo(tooltip);
                    squareVisual.setFill(Color.GRAY);
                });
            }
        }

        return matrixPane;
    }

    public ScrollPane createScrollPane(Pane content) {
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

            event.consume();
        });

        return scrollPane;
    }

    private void showSquareInfo(Square square, Tooltip tooltip) {
        tooltip.setText(square.toString());
        tooltip.show(matrixPane.getScene().getWindow(), square.getX() * SQUARE_SIZE * 1.5, square.getY() * SQUARE_SIZE * 1.5);
    }

    private void hideSquareInfo(Tooltip tooltip) {
        tooltip.hide();
    }
}
