package com.javarush.island.levchuk.LiveActions;

import com.javarush.island.levchuk.IslandMap.Area;
import com.javarush.island.levchuk.IslandMap.Location;

public interface Movable {
    public void move(Area area, Location location);
}
