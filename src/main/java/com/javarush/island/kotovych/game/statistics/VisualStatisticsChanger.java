package com.javarush.island.kotovych.game.statistics;

import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.util.EmojiTable;
import javafx.application.Platform;
import javafx.scene.control.Label;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class VisualStatisticsChanger{
    Label totalOrganisms;
    Label organismCount;

    public VisualStatisticsChanger(Label totalOrganisms, Label organismsCount){
        this.totalOrganisms = totalOrganisms;
        this.organismCount = organismsCount;
    }

    public void update(AtomicInteger totalValue, Map<String, AtomicInteger> totalOrganismCount) {
        Platform.runLater(() -> totalOrganisms.setText(String.valueOf(totalValue.get())));
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, AtomicInteger> entry : totalOrganismCount.entrySet()) {
            int value = entry.getValue().get();
            if (value != 0) {
                builder.append("%s: %d\n".formatted(EmojiTable.getEmoji(entry.getKey()), entry.getValue().get()));
            }
        }
        Platform.runLater(() -> organismCount.setText(builder.toString()));
    }
}
