package com.javarush.island.khasanov.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class Position implements Comparable<Position> {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position position) {
        this.x = position.x;
        this.y = position.y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public int compareTo(Position o) {
        int res = this.x - o.x;
        return res == 0 ? this.y - o.y : res;
    }
}
