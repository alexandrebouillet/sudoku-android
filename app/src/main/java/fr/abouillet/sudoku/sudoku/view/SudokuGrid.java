package fr.abouillet.sudoku.sudoku.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import fr.abouillet.sudoku.sudoku.activity.GameActivity;
import fr.abouillet.sudoku.sudoku.R;

public class SudokuGrid extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private float gridWidth;
    private float gridSeparatorSize;
    private float cellWidth;

    private Paint blackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private GameActivity gameActivity;

    private int[][] board;
    private int level = GameActivity.level;
    private int grid = GameActivity.grid;

    private List<Point> enableCaseList = new ArrayList();

    public SudokuGrid(Context context) {
        this(context, null);
        gameActivity = (GameActivity) context;
    }

    public SudokuGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
        gameActivity = (GameActivity) context;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        gridSeparatorSize = (w / 9f) / 20f;
        gridWidth = w;
        cellWidth = gridWidth / 9f;
        board = this.getSudokuGrid(level, grid);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        blackPaint.setTextSize(125);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(!(board[i][j] == 0)){
                    canvas.drawText(String.valueOf(board[i][j]), (j) * cellWidth + 25, (i + 1) * cellWidth -10, blackPaint);
                }else{
                    enableCaseList.add(new Point(i, j));
                }
                if(!(board[i][j] == 0) && enableCaseList.contains(new Point(i,j))){
                    Paint paintTester = new Paint(Paint.ANTI_ALIAS_FLAG);
                    paintTester.setTextSize(125);
                    if (gameActivity.cellChecker(i, j, board)) {
                        paintTester.setColor(Color.GREEN);
                    } else {
                        paintTester.setColor(Color.RED);
                    }
                    canvas.drawText(String.valueOf(board[i][j]), (j) * cellWidth + 25, (i + 1) * cellWidth -10, paintTester);
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
            int value = gameActivity.getValue();

            if(!enableCaseList.contains(new Point(row, column))){

            }
            else if (value != 0) {
                board[row][column] = value;
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

    public int[][] getBoard() {
        return board;
    }

}
