package com.javarush.island.kotovych.game.visual;

import com.javarush.island.kotovych.exceptions.AppException;
import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.game.Square;
import com.javarush.island.kotovych.game.statistics.Statistics;
import com.javarush.island.kotovych.game.statistics.VisualStatisticsChanger;
import com.javarush.island.kotovych.settings.Settings;
import com.javarush.island.kotovych.util.Constants;
import com.javarush.island.kotovych.util.ShowAlert;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class VisualGameScene {
    GameScene gameScene;
    private static final int SQUARE_SIZE = 60;
    private static final double MIN_SCALE = 0.2;
    private static final double MAX_SCALE = 2.0;

    private Pane matrixPane;
    private double scaleFactor = 1.0;

    Label totalOrganismsLabel;
    Label totalOrganismCountLabel;
    ScheduledExecutorService statisticsChangerExecutor = Executors.newScheduledThreadPool(1);

    Statistics statistics;
    VisualStatisticsChanger visualStatisticsChanger;
    public VisualGameScene(GameScene gameScene) {
        this.gameScene = gameScene;
        this.statistics = gameScene.getStatistics();
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

        Button startStopButton = new Button(Constants.START);
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

    public GridPane createInformationPanel() {
        GridPane informationPanel = new GridPane();

        Label organismsLabel = new Label("Organisms:");
        totalOrganismsLabel = new Label("0");
        totalOrganismCountLabel = new Label();
        totalOrganismsLabel.setStyle("-fx-font-weight: bold");

        informationPanel.add(organismsLabel, 0, 0);
        informationPanel.add(totalOrganismsLabel, 1, 0);
        informationPanel.add(totalOrganismCountLabel, 0, 1);

        informationPanel.setAlignment(Pos.TOP_RIGHT);
        informationPanel.setHgap(5);
        informationPanel.setVgap(5);
        informationPanel.setPadding(new Insets(10, 10, 0, 0));

         visualStatisticsChanger = new VisualStatisticsChanger(statistics, totalOrganismsLabel, totalOrganismCountLabel);

        visualStatisticsChanger.update(statistics.getTotalOrganisms(), statistics.getTotalOrganismCount());

        return informationPanel;
    }

    private void startStatisticsChanging(){
        Statistics statistics = gameScene.getStatistics();
        statisticsChangerExecutor = Executors.newScheduledThreadPool(1);
        visualStatisticsChanger = new VisualStatisticsChanger(statistics, totalOrganismsLabel, totalOrganismCountLabel);
        statisticsChangerExecutor.scheduleAtFixedRate(visualStatisticsChanger, 0, Settings.getDelay(), TimeUnit.MILLISECONDS);
    }

    private void stopStatisticsChanging(){
        try {
            statisticsChangerExecutor.shutdown();
            if (!statisticsChangerExecutor.awaitTermination(2, TimeUnit.SECONDS)) {
                statisticsChangerExecutor.shutdownNow();
            }
        } catch (InterruptedException e){
            throw new AppException(e);
        }
    }

    private void toggleStartStop(Button startStopButton, TextField inputField) {
        String input = inputField.getText();

        if (startStopButton.getText().equals(Constants.START)) {
            try {
                Settings.setDelay(Integer.parseInt(input));
            } catch (NumberFormatException e) {
                if (input.isEmpty()) {
                    Settings.setDelay(Settings.getDelay());
                } else {
                    ShowAlert.showErrorAlert(Constants.NUMBER_CANNOT_BE_STRING);
                    return;
                }
            }
            startStopButton.setText(Constants.STOP);
            gameScene.startAllRequiredControllers();
            startStatisticsChanging();
        } else {
            startStopButton.setText(Constants.START);
            gameScene.stopControllers();
            stopStatisticsChanging();
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
