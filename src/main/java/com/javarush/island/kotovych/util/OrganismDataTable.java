package com.javarush.island.kotovych.util;

import com.javarush.island.kotovych.exceptions.AppException;
import com.javarush.island.kotovych.organisms.Organism;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

public class OrganismDataTable {
    private static ObjectMapper mapper = new ObjectMapper();
    private OrganismDataTable(){}

    private static Map<String, Map<String, Double>> dataTable;

    static {
        Class<OrganismDataTable> organismDataTableClass = OrganismDataTable.class;
        URL url =  organismDataTableClass.getResource(Constants.ORGANISM_DATA_TABLE);
        try {
            dataTable = mapper.readValue(url, new TypeReference<Map<String, Map<String, Double>>>() {});
        } catch (IOException e) {
            throw new AppException(e);
        }
    }


    public static Map<String, Double> getData(Organism source){
        Map<String, Double> result = dataTable.get(source.getName());
        return result;
    }
}
