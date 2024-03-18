package com.javarush.island.boyarinov;

import com.javarush.island.boyarinov.constants.Constants;
import com.javarush.island.boyarinov.entities.map.Island;
import com.javarush.island.boyarinov.service.*;
import com.javarush.island.boyarinov.view.View;

import java.util.List;

public class ConsoleRunner {

    public static void main(String[] args) {
        int row = Constants.ROW;
        int column = Constants.COLUMN;
        Island island = new Island(row, column);
        island.fillIsland();

        StatisticService statisticService = new StatisticService(island);
        View view = new View(statisticService);

        List<Runnable> services = List.of(
                new MovingService(island),
                new EatingService(island),
                new MultiplyingService(island),
                new FillingMapService(island),
                new ViewService(view)
        );
        GameService gameService = new GameService(services);
        gameService.start();

    }
}
