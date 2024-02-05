package com.javarush.island.levchuk.IslandMap;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Area {
    private Location[][] locations;

    public Area(int x, int y){
        locations = new Location[x][y];
    }




}
