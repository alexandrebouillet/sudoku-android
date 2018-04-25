package fr.abouillet.sudoku.sudoku;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import fr.abouillet.sudoku.sudoku.model.SudokuGrid;

public class GameActivity extends AppCompatActivity {
    public static int grid;
    public static int level;
    private int value = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        int grid = this.getIntent().getIntExtra("grid", 0);
        int level = this.getIntent().getIntExtra("level", 1);

        for(int i = 1 ; i <= 9 ; i++) {
            TextView tv = (TextView)findViewById(this.getResources().getIdentifier("tv" + i, "id", getPackageName()));
            tv.setOnClickListener(getValue);
        }
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
