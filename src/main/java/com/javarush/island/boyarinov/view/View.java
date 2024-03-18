package com.javarush.island.boyarinov.view;

import com.javarush.island.boyarinov.constants.Constants;
import com.javarush.island.boyarinov.service.StatisticService;

import java.util.Map;

public class View {

    private final StatisticService statistic;

    public View(StatisticService statistic) {
        this.statistic = statistic;
    }

    public void showStatistic() {
        statistic.updateStatistic();
        Map<String, Integer> statisticMap = statistic.getStatistic();
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : statisticMap.entrySet()) {
            String organismName = entry.getKey();
            String icon = getOrganismIcon(organismName);
            int numberOrganisms = entry.getValue();
            stringBuilder
                    .append(icon)
                    .append(": ")
                    .append(numberOrganisms)
                    .append("; ");
        }
        stringBuilder.append("\n")
                .append("-".repeat(170));
        System.out.println(stringBuilder);
    }

    private String getOrganismIcon(String organismName) {
        Map<String, Integer> animalIndexInTable = Constants.getAnimalIndexInTable();
        String[] animalIcon = Constants.ANIMAL_ICON;
        int organismIndex = animalIndexInTable.get(organismName);
        return animalIcon[organismIndex];
    }
}
