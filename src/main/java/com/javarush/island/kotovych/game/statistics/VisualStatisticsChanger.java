package com.javarush.island.kotovych.game.statistics;

import com.javarush.island.kotovych.exceptions.AppException;
import com.javarush.island.kotovych.util.EmojiTable;
import com.javarush.island.kotovych.util.ShowAlert;
import javafx.scene.control.Label;

import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class VisualStatisticsChanger {
    Label totalOrganisms;
    Label organismCount;

    public VisualStatisticsChanger(Label totalOrganisms, Label organismCount) {
        this.totalOrganisms = totalOrganisms;
        this.organismCount = organismCount;
    }

    Semaphore semaphore = new Semaphore(1);

    public void update(AtomicInteger totalValue, Map<String, AtomicInteger> totalOrganismCount) {
        totalOrganisms.setText(String.valueOf(totalValue.get()));
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, AtomicInteger> entry : totalOrganismCount.entrySet()) {
            int value = entry.getValue().get();
            if (value != 0) {
                builder.append("%s: %d\n".formatted(EmojiTable.getEmoji(entry.getKey()), entry.getValue().get()));
            }
        }
        organismCount.setText(builder.toString());
    }
}
