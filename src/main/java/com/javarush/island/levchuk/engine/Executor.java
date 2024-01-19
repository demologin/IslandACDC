package com.javarush.island.levchuk.engine;

import com.javarush.island.levchuk.Const.Constants;
import com.javarush.island.levchuk.IslandMap.Area;
import com.javarush.island.levchuk.utils.EntityFactory;
import com.javarush.island.levchuk.utils.AppConfigurator;
import com.javarush.island.levchuk.utils.ConsoleProvider;
import com.javarush.island.levchuk.utils.StatisticProvider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.javarush.island.levchuk.Const.Constants.MAX_NUMBER_SIMULATION_DAYS;

public class Executor {
    private final ConsoleProvider consoleProvider = new ConsoleProvider();
    private final StatisticProvider statisticProvider = new StatisticProvider(consoleProvider);
    private final EntityFactory entityFactory = new EntityFactory();
    private final ExecutorService executorService = Executors.newWorkStealingPool();
    private final TaskHandler taskHandler = new TaskHandler();

    public void startSimulation() {
        consoleProvider.println("--- init game ---");
        int numberSimulationDays = getNumberSimulationDays();
        Area area = new AppConfigurator(entityFactory,consoleProvider).init();
        consoleProvider.println("--- start simulation ---");
        statisticProvider.printIslandArea(area);
        statisticProvider.printByCell(area);
        long start = System.currentTimeMillis();
        try {
            for (int i = 1; i <= numberSimulationDays; i++) {
                consoleProvider.println("************ day " + i + " ************");
                taskHandler.eatAllInArea(area, executorService);
                taskHandler.reproduceAllInArea(area, executorService);
                taskHandler.moveAllInArea(area, executorService);
                statisticProvider.printIslandArea(area);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        long end = System.currentTimeMillis();
        consoleProvider.println("--- end simulation ---");
        statisticProvider.printByCell(area);
        statisticProvider.printIslandArea(area);
        consoleProvider.println("Total time: " + (end - start) * 1.0 / 1000 + " s");
        executorService.shutdown();



    }

    private int getNumberSimulationDays() {
        consoleProvider.print("Enter number simulation days (1:" + MAX_NUMBER_SIMULATION_DAYS + "): ");
        String inputLine = consoleProvider.read();
        if (inputLine != null) {
            try {
                int size = Integer.parseInt(inputLine);
                if (size > 0 && size <= MAX_NUMBER_SIMULATION_DAYS) {
                    return size;
                }
                throw new IllegalArgumentException("Invalid input size. Check input data. ");
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Input is not a number. Check input data. ");
            }
        }
        throw new IllegalArgumentException("Invalid input data. Check input data. ");
    }
}
