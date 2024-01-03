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
    private static ObjectMapper mapper = new ObjectMapper();
    private ProbabilityTable(){}

    private static Map<String, Map<String, Integer>> probabilityTable;

    static {
        Class<ProbabilityTable> probabilityTableClass = ProbabilityTable.class;
        URL url = probabilityTableClass.getResource(Constants.PROBABILITY_TABLE);
        try {
            probabilityTable = mapper.readValue(url, new TypeReference<Map<String, Map<String, Integer>>>() {});
        } catch (IOException e) {
            throw new AppException(e);
        }
    }


    public static int getProbability(Organism predator, Organism prey){
        Map<String, Integer> predatorMap = probabilityTable.get(predator.getName());
        int probability = predatorMap.get(prey.getName());
        return probability;
    }
}
