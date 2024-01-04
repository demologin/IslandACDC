package com.javarush.island.kotovych.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.island.kotovych.exceptions.AppException;
import com.javarush.island.kotovych.organisms.Organism;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class EmojiTable {
    private static final ObjectMapper mapper = new ObjectMapper();

    private EmojiTable(){}

    private static final Map<String, String> emojiTable;

    static {
        Class<EmojiTable> emojiTableClass = EmojiTable.class;
        URL url =  emojiTableClass.getResource(Constants.EMOJI_TABLE);
        try {
            emojiTable = mapper.readValue(url, new TypeReference<Map<String, String>>() {});
        } catch (IOException e) {
            throw new AppException(e);
        }
    }

    public static String getEmoji(String source){
        return emojiTable.get(source);
    }
}
