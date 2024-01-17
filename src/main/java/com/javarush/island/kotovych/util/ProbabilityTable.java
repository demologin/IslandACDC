package com.javarush.island.kotovych.util;

import com.javarush.island.kotovych.exceptions.AppException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.island.kotovych.organisms.Organism;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

public class ProbabilityTable {
    private static final ObjectMapper mapper = new ObjectMapper();
    private ProbabilityTable(){}

    private static final Map<String, Map<String, Integer>> probabilityTable;

    static {
        Class<ProbabilityTable> probabilityTableClass = ProbabilityTable.class;
        URL url = probabilityTableClass.getResource(Constants.PROBABILITY_TABLE);
        try {
            probabilityTable = mapper.readValue(url, new TypeReference<Map<String, Map<String, Integer>>>() {});
        } catch (IOException e) {
            throw new AppException(e);
        }
    }


    public static int getProbability(String predator, String prey){
        Map<String, Integer> predatorMap = probabilityTable.get(predator);
        int probability = predatorMap.get(prey);
        return probability;
    }
}
