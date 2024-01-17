package com.javarush.island.boyarinov.entities.map;


public class Island {

    private int row;
    private int column;
    private Cell[][] map;

    public Island(int row, int column) {
        this.row = row;
        this.column = column;
        initializeMap();
    }

    public Cell[][] getMap() {
        return map;
    }

    private void initializeMap() {
        map = new Cell[this.row][this.column];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Cell();
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j].initAvailableCells(this, i, j);
            }
        }
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
