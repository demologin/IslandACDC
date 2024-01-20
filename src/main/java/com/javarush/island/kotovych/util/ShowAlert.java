package com.javarush.island.kotovych.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ShowAlert {
    private static boolean blocked = false;
    public static void showErrorAlert(String message) {
        if(!blocked) {
            blocked = true;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
            blocked = false;
        }
    }

    public static void showErrorWithStacktrace(String message, Exception ex) {
        if(!blocked) {
            blocked = true;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(Constants.EXCEPTION);
            alert.setHeaderText(null);
            alert.setContentText(message);

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String exceptionText = sw.toString();

            Label label = new Label(Constants.EXCEPTION_STACKTRACE_WAS_TEXT);

            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);
            alert.getDialogPane().setExpandableContent(expContent);

            alert.showAndWait();
            blocked = false;
        }
    }
}
