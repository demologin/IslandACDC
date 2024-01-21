package com.javarush.island.khasanov;

import com.javarush.island.khasanov.config.Setting;
import com.javarush.island.khasanov.entity.Island;
import com.javarush.island.khasanov.service.SimulationWorker;
import com.javarush.island.khasanov.view.ConsoleView;
import com.javarush.island.khasanov.view.Statistics;
import com.javarush.island.khasanov.view.View;

public class ConsoleRunner {
    public static void main(String[] args) {
        Island island = new Island(Setting.width, Setting.height);
        View view = new ConsoleView(island);
        Statistics statistics = new Statistics(island);
        SimulationWorker simulation = new SimulationWorker(island, view, statistics);
        simulation.run();
    }
}
