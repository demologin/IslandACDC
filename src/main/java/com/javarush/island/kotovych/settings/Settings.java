package com.javarush.island.kotovych.settings;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.island.kotovych.util.Constants;
import com.javarush.island.kotovych.util.ShowAlert;
import javafx.scene.control.Alert;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Settings {
    private static Map<String, Integer> settings = new HashMap<>();
    private static final ObjectMapper mapper = new ObjectMapper();

    @Getter
    @Setter
    private static int delay;

    @Getter
    private static int maxAnimalsOnSquare;

    @Getter
    private static int animalsOnSquareAtTheBeginning;

    @Getter
    private static int gameWidth;

    @Getter
    private static int gameHeight;

    private Settings() {
    }

    static {
        loadFromDefault();
        loadFromJson();
    }

    private static void loadFromDefault() {
        delay = DefaultSettings.delay;
        maxAnimalsOnSquare = DefaultSettings.maxAnimalsOnSquare;
        animalsOnSquareAtTheBeginning = DefaultSettings.animalsOnSquareAtTheBeginning;
        gameWidth = DefaultSettings.gameWidth;
        gameHeight = DefaultSettings.gameHeight;
    }

    private static void loadFromJson(){
        Class<Settings> settingsClass = Settings.class;
        URL resource = settingsClass.getResource(Constants.SETTINGS);
        try {
            settings = mapper.readValue(resource, new TypeReference<Map<String, Integer>>() {});
            delay = settings.get("delay");
            maxAnimalsOnSquare = settings.get("maxOnSquare");
            animalsOnSquareAtTheBeginning = settings.get("animalsOnSquareAtTheBeginning");
            gameWidth = settings.get("gameWidth");
            gameHeight = settings.get("gameHeight");

        } catch (IOException e) {
            ShowAlert.showErrorAlert("Failed to load settings");
        }
    }
}
