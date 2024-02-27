package com.javarush.island.boyarinov.view;

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
            int beginIndex = 0;
            int endIndex = 2;
            String namePrefix = organismName.substring(beginIndex, endIndex);
            int numberOrganisms = entry.getValue();
            stringBuilder
                    .append(namePrefix)
                    .append(": ")
                    .append(numberOrganisms)
                    .append("; ");
        }
        stringBuilder.append("\n")
                .append("-".repeat(150));
        System.out.println(stringBuilder);
    }
}
