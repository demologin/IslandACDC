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
    private static Map<String, Long> settings = new HashMap<>();
    private static final ObjectMapper mapper = new ObjectMapper();

    private Settings(){}

    static {
        Class<Settings> settingsClass = Settings.class;
        URL resource = settingsClass.getResource(Constants.SETTINGS);
        try {
            settings = mapper.readValue(resource, new TypeReference<Map<String, Long>>() {});
        } catch (IOException e){
            ShowAlert.showErrorAlert("Failed to load settings");
        }
    }

    public static long get(String key){
        return settings.get(key);
    }

    public static void set(String key, long value){
        settings.put(key, value);
    }
}
