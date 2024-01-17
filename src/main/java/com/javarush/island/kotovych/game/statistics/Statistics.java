package com.javarush.island.kotovych.game.statistics;

import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.organisms.Organism;
import com.javarush.island.kotovych.util.ShowAlert;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class Statistics {
    private GameScene gameScene;

    private AtomicInteger totalOrganisms = new AtomicInteger(0);
    private Map<String, AtomicInteger> totalOrganismCount = new ConcurrentHashMap<>();

    VisualStatisticsChanger visualStatisticsChanger;

    boolean initializedVisualChanger = false;

    public void setVisualStatisticsChanger(VisualStatisticsChanger visualStatisticsChanger){
        this.visualStatisticsChanger = visualStatisticsChanger;
        initializedVisualChanger = true;
        try{
            visualStatisticsChanger.update(totalOrganisms, totalOrganismCount);
        } catch (Exception e){
            ShowAlert.showErrorAlert("Failed to update information.");
        }
    }

    public Statistics(GameScene gameScene) {
        this.gameScene = gameScene;
    }

    public void addOrganism(String name, int number) {
        totalOrganisms.addAndGet(number);
        totalOrganismCount.putIfAbsent(name, new AtomicInteger(0));
        totalOrganismCount.get(name).addAndGet(number);
        if(initializedVisualChanger) {
            try {
                visualStatisticsChanger.update(totalOrganisms, totalOrganismCount);
            } catch (Exception e) {
                ShowAlert.showErrorAlert("Failed to update information");
            }
        }
    }

    public void removeOrganism(String name, int number) {
        totalOrganisms.addAndGet(-number);
        totalOrganismCount.get(name).addAndGet(-number);
        try{
            visualStatisticsChanger.update(totalOrganisms, totalOrganismCount);
        } catch (Exception e){
            ShowAlert.showErrorAlert("Failed to update information");
        }
    }
}
