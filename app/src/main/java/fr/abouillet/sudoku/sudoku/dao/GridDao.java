package fr.abouillet.sudoku.sudoku.dao;

import java.util.ArrayList;
import java.util.List;

import fr.abouillet.sudoku.sudoku.model.Grid;

public class GridDao {

    public GridDao() {

    }

    public ArrayList<Grid> getListGridByLevel(int level){
        ArrayList<Grid> list = new ArrayList<>();
        for (int i = 0; i< 100 ; i++){
            String name = "Niveau:"+ level+":"+i;
            Grid grid = new Grid(name, i);
            list.add(grid);

        }

        return list;
    }


}
