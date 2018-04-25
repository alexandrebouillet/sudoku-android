package fr.abouillet.sudoku.sudoku.model;

import java.io.Serializable;

public class Grid implements Serializable {

    private String name;

    private String grid;

    public Grid() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }
}
