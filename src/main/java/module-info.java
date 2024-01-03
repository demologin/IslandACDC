module island {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires lombok;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires com.fasterxml.jackson.core;

    opens com.javarush.island.kotovych.runner to javafx.fxml;
    exports com.javarush.island.kotovych.runner;
}
