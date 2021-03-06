package fr.abouillet.sudoku.sudoku.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fr.abouillet.sudoku.sudoku.R;
import fr.abouillet.sudoku.sudoku.view.SudokuGrid;

public class GameActivity extends AppCompatActivity {
   public static int level;
   public static int grid;

    private int value = 0;
    private SudokuGrid sudokuGrid;
    private Button checkButton;
    private TextView validText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        grid = getIntent().getIntExtra("grid", 0);
        level = getIntent().getIntExtra("level", 1);
        Log.d("GRID:", String.valueOf(grid));
        Log.d("LEVEL:", String.valueOf(level));
        sudokuGrid = findViewById(R.id.sudoku);
        checkButton = findViewById(R.id.check);
        validText = findViewById(R.id.validText);

        for(int i = 1 ; i <= 9 ; i++) {
            TextView tv = findViewById(this.getResources().getIdentifier("tv" + i, "id", getPackageName()));
            tv.setOnClickListener(getValue);
        }

        this.checkButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean gridValid = gridChecker(sudokuGrid.getBoard());
                if(gridValid){
                    validText.setTextColor(Color.GREEN);
                    validText.setText("Vous avez gagné !");
                }else{
                    validText.setTextColor(Color.RED);
                    validText.setText("Perdu ! Encore un petit effort !");
                }
            }
        });


    }

    View.OnClickListener getValue = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            TextView tv = findViewById(v.getId());
            value = Integer.parseInt(tv.getText().toString());

            for(int i = 1 ; i <= 9 ; i++) {
                int valID = getResources().getIdentifier("tv" + i, "id", getPackageName());
                TextView tvw = findViewById(valID);
                tvw.setBackgroundColor(Color.TRANSPARENT);
            }

            tv.setBackgroundColor(Color.CYAN);
        }
    };

    public boolean gridChecker(int[][] grid) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (grid[i][j] < 1 || grid[i][j] > 9
                        || !cellChecker(i, j, grid))
                    return false;
        return true;
    }

    public boolean cellChecker(int i, int j, int[][] grid) {
        for (int column = 0; column < 9; column++)
            if (column != j && grid[i][column] == grid[i][j])
                return false;

        for (int row = 0; row < 9; row++)
            if (row != i && grid[row][j] == grid[i][j])
                return false;

        for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++)
            for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++)
                if (row != i && col != j && grid[row][col] == grid[i][j])
                    return false;

        return true;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
