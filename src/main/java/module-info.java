module island {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires lombok;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires com.fasterxml.jackson.core;

    opens com.javarush.island.kotovych.game.visual to javafx.fxml;
    exports com.javarush.island.kotovych.game.visual;
    exports com.javarush.island.kotovych;
    opens com.javarush.island.kotovych to javafx.fxml;
    exports com.javarush.island.kotovych.game;
    exports com.javarush.island.kotovych.organisms;
    exports com.javarush.island.kotovych.controllers;
    exports com.javarush.island.kotovych.game.statistics;
}
