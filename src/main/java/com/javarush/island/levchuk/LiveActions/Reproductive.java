package com.javarush.island.levchuk.LiveActions;

import com.javarush.island.levchuk.IslandMap.Location;
import com.javarush.island.levchuk.entity.Entity;

public interface Reproductive {
    public <T extends Entity> T reproduce(Location location);
}
