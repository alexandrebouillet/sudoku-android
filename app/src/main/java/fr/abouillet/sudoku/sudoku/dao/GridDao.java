package fr.abouillet.sudoku.sudoku.dao;

import java.util.ArrayList;
import java.util.List;

import fr.abouillet.sudoku.sudoku.model.Grid;

public class GridDao {

    List<Grid> listGrid = new ArrayList<>();

    public GridDao() {

    }

    public List<Grid> getListGrid() {
        return listGrid;
    }

    public ArrayList<String> getListGridByLevel(int level){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i< 100 ; i++){
            String grid = "Niveau:"+ level+":"+i;
            list.add(grid);

        }

        return list;
    }


}
