package com.javarush.island.kotovych.game.statistics;

import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.util.EmojiTable;
import javafx.scene.control.Label;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class VisualStatisticsChanger implements Runnable{
    Label totalOrganisms;
    Label organismCount;
    GameScene gameScene;

    Statistics statistics;
    public VisualStatisticsChanger(GameScene gameScene, Label totalOrganisms, Label organismCount) {
        this.totalOrganisms = totalOrganisms;
        this.organismCount = organismCount;
        this.gameScene = gameScene;
        statistics = gameScene.getStatistics();
    }

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


    @Override
    public void run() {
        update(statistics.getTotalOrganisms(), statistics.getTotalOrganismCount());
    }
}
