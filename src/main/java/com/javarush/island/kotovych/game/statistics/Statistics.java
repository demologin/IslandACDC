package com.javarush.island.kotovych.game.statistics;

import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.organisms.Organism;
import com.javarush.island.kotovych.util.Constants;
import com.javarush.island.kotovych.util.ShowAlert;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class Statistics {
    private AtomicInteger totalOrganisms = new AtomicInteger(0);
    private Map<String, AtomicInteger> totalOrganismCount = new ConcurrentHashMap<>();

    VisualStatisticsChanger visualStatisticsChanger;

    boolean initializedVisualChanger = false;

    public void setVisualStatisticsChanger(VisualStatisticsChanger visualStatisticsChanger){
        this.visualStatisticsChanger = visualStatisticsChanger;
        initializedVisualChanger = true;
        visualStatisticsChanger.update(totalOrganisms, totalOrganismCount);
    }

    public void addOrganism(String name, int number) {
        totalOrganisms.addAndGet(number);
        totalOrganismCount.putIfAbsent(name, new AtomicInteger(0));
        totalOrganismCount.get(name).addAndGet(number);
        if(initializedVisualChanger) {
            try {
                visualStatisticsChanger.update(totalOrganisms, totalOrganismCount);
            } catch (Exception e){
                ShowAlert.showErrorAlert(Constants.FAILED_TO_UPDATE_STATISTICS);
            }
        }
    }

    public void removeOrganism(String name, int number) {
        totalOrganisms.addAndGet(-number);
        totalOrganismCount.get(name).addAndGet(-number);
        if(initializedVisualChanger) {
            try {
                visualStatisticsChanger.update(totalOrganisms, totalOrganismCount);
            } catch (Exception e){
                ShowAlert.showErrorAlert(Constants.FAILED_TO_UPDATE_STATISTICS);
            }
        }
    }
}
