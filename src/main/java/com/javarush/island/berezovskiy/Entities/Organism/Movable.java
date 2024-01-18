package com.javarush.island.berezovskiy.Entities.Organism;

import com.javarush.island.berezovskiy.Entities.Cell.Cell;

public interface Movable {
    int[] move(Cell cell);
}
