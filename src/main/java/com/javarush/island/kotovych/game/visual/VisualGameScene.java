package com.javarush.island.kotovych.game.visual;

import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.game.Square;
import com.javarush.island.kotovych.settings.Settings;
import com.javarush.island.kotovych.util.ShowAlert;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VisualGameScene {
    GameScene gameScene;
    private static final int SQUARE_SIZE = 60;
    private static final double MIN_SCALE = 0.2;
    private static final double MAX_SCALE = 2.0;

    private Pane matrixPane;
    private double scaleFactor = 1.0;

    public VisualGameScene(GameScene gameScene) {
        this.gameScene = gameScene;
    }

    public Pane createMatrixPane() {
        int ROWS = gameScene.getHeight();
        int COLS = gameScene.getWidth();
        matrixPane = new Pane();
        for (int col = 0; col < COLS; col++) {
            for (int row = 0; row < ROWS; row++) {
                Square square = gameScene.getSquareByCoordinates(col, row);

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

                squareVisual.setOnMouseClicked(event -> {
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

    public HBox createControlPanel() {
        HBox controlPanel = new HBox();

        TextField inputField = new TextField();
        inputField.setPromptText("Enter input...");

        Button startStopButton = new Button("Start");
        startStopButton.setOnAction(event -> toggleStartStop(startStopButton, inputField));

        startStopButton.setMinHeight(40);
        startStopButton.setMinWidth(120);

        controlPanel.setAlignment(Pos.CENTER_RIGHT);
        controlPanel.setSpacing(10);

        controlPanel.setPadding(new Insets(0, 10, 0, 0));

        controlPanel.getChildren().addAll(inputField, startStopButton);

        controlPanel.setMinHeight(50);

        return controlPanel;
    }

    private void toggleStartStop(Button startStopButton, TextField inputField) {
        String input = inputField.getText();

        if (startStopButton.getText().equals("Start")) {
            try {
                Settings.set("delay", (Long.parseLong(input)));
            } catch (NumberFormatException e) {
                if (input.isEmpty()) {
                    Settings.set("delay", Settings.get("delay"));
                } else {
                    ShowAlert.showErrorAlert("Number cannot be string");
                    return;
                }
            }
            startStopButton.setText("Stop");
            gameScene.startAllRequiredControllers();
        } else {
            startStopButton.setText("Start");
            gameScene.stopControllers();
        }
    }

    private void showSquareInfo(Square square, Tooltip tooltip) {
        tooltip.setText(square.toString());
        tooltip.show(matrixPane.getScene().getWindow(), square.getX() * SQUARE_SIZE * 1.5, square.getY() * SQUARE_SIZE * 1.5);
    }

    private void hideSquareInfo(Tooltip tooltip) {
        tooltip.hide();
    }
}
