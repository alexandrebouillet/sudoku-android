package fr.abouillet.sudoku.sudoku.model;

import java.io.Serializable;

public class Grid implements Serializable {

    private String name;

    private int grid;

    private int completePourcent;

    public Grid(String name, int grid) {
        this.name = name;
        this.grid = grid;
        this.completePourcent = (int) ((Math.random())*100);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrid() {
        return grid;
    }

    public void setGrid(int grid) {
        this.grid = grid;
    }

    public int getCompletePourcent() {
        return completePourcent;
    }

    public void setCompletePourcent(int completePourcent) {
        this.completePourcent = completePourcent;
    }
}
