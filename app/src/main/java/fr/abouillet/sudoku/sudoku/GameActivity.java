package fr.abouillet.sudoku.sudoku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import fr.abouillet.sudoku.sudoku.model.SudokuGrid;

public class GameActivity extends AppCompatActivity {
    public static int grid;
    public static int level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        int grid = this.getIntent().getIntExtra("grid", 0);
        int level = this.getIntent().getIntExtra("level", 1);
        Log.d("grid", String.valueOf(grid));

    }
}
