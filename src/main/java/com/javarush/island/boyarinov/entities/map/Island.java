package com.javarush.island.boyarinov.entities.map;


public class Island {

    private int row;
    private int column;
    private Cell[][] map;

    public Island(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Cell[][] getMap() {
        if (map == null) {
            map = new Cell[this.row][this.column];
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    map[i][j] = new Cell();
                }
            }
        }
        return map;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
