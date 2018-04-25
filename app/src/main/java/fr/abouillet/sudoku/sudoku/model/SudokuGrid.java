package fr.abouillet.sudoku.sudoku.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import fr.abouillet.sudoku.sudoku.GameActivity;
import fr.abouillet.sudoku.sudoku.R;

public class SudokuGrid extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private float gridWidth;
    private float gridSeparatorSize;
    private float cellWidth;
    private float cellHeight;
    private float gridHeight;

    private Paint blackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int level = GameActivity.level;
    private int grid = GameActivity.grid;

    private int[][] board;

    private List<Point> disableCaseList = new ArrayList();

    public SudokuGrid(Context context) {
        this(context, null);
    }

    public SudokuGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        gridSeparatorSize = (w / 9f) / 20f;
        gridHeight = h;
        gridWidth = w;
        cellHeight = gridHeight /9f;
        cellWidth = gridWidth / 9f;
        board = this.getSudokuGrid(level, grid);
        blackPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        blackPaint.setTextSize(150);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(!(board[i][j] == 0)){
                    canvas.drawText(String.valueOf(board[i][j]), (j) * cellWidth + 25, (i + 1) * cellWidth - 25, blackPaint);
                }else{
                    disableCaseList.add(new Point(i, j));
                }
            }
        }

        paint.setTextAlign( Paint.Align.CENTER );
        paint.setStrokeWidth(5);
        for( int i=0; i<=9; i++ ) {
            canvas.drawLine( i*cellWidth, 0, i*cellWidth, cellWidth*9, paint );
            canvas.drawLine( 0,i*cellWidth, cellWidth*9, i*cellWidth, paint );
        }
        paint.setColor( Color.BLACK );
        paint.setStrokeWidth( gridSeparatorSize );
        for( int i=0; i<=3; i++ ) {
            canvas.drawLine( i*(cellWidth*3), 0, i*(cellWidth*3), cellWidth*9, paint );
            canvas.drawLine( 0,i*(cellWidth*3), cellWidth*9, i*(cellWidth*3), paint );
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int column = (int)(event.getX() / cellWidth);
            int row = (int)(event.getY() / cellWidth);

            if(!disableCaseList.contains(new Point(row, column))){

            }

            invalidate();
        }

        return true;
    }

    private int[][] getSudokuGrid(int lvl, int num){
        int[][] Sudo = new int[9][9];
        String line = this.getLigne(lvl,num);
        String[] lineArray = line.split("");
        int k = 1;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                Sudo[i][j] = Integer.parseInt(lineArray[k]);
                k++;
            }
        }
        return Sudo;
    }

    private String getLigne(int lvl, int num) {
        int fileResourceId;
        if (lvl == 1){
            fileResourceId = R.raw.niveau1;
        } else {
            fileResourceId = R.raw.niveau2;
        }
        InputStream is = this.getResources().openRawResource(fileResourceId);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String str="";
        String ligne="";
        int i = 0;

        try {
            while ((str = reader.readLine()) != null) {
                if ( i == num){
                    ligne = str;
                }
                i++;
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } return ligne;
    }

}
