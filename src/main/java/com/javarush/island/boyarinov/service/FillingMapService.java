package com.javarush.island.boyarinov.service;

import com.javarush.island.boyarinov.constants.Constants;
import com.javarush.island.boyarinov.entities.map.Island;
import com.javarush.island.boyarinov.util.RandomNum;

public class FillingMapService extends AbstractService {

    private final Island island;

    public FillingMapService(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        if (RandomNum.get(Constants.CHANCE_FILLING)) {
            island.fillIsland();
        }
    }
}
