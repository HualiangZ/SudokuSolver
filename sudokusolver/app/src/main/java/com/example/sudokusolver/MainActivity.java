package com.example.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Board board;
    private Graph g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        board = findViewById(R.id.sudokuBoard);
        g = board.getGraph();
    }

    public void btnSolve(View view){
        g.solver();
        g.setSolveBoard();
        g.printBoard();
        board.invalidate();
    }

    public void btnClearBoard(View view){
        g.clearBoard();
        board.invalidate();
    }


    public void btnClear(View view){
        g.addNum(0);
        board.invalidate();
    }


    public void btnOne(View view){
        if(g.addNum(1)) {
            board.invalidate();
        }
    }

    public void btnTwo(View view){
        if(g.addNum(2)) {
            board.invalidate();
        }
    }

    public void btnThree(View view){
        if(g.addNum(3)) {
            board.invalidate();
        }
    }

    public void btnFour(View view){
        if(g.addNum(4)) {
            board.invalidate();
        }
    }

    public void btnFive(View view){
        if(g.addNum(5)) {
            board.invalidate();
        }
    }

    public void btnSix(View view){
        if(g.addNum(6)) {
            board.invalidate();
        }
    }

    public void btnSeven(View view){
        if(g.addNum(7)) {
            board.invalidate();
        }
    }

    public void btnEight(View view){
        if(g.addNum(8)) {
            board.invalidate();
        }
    }

    public void btnNine(View view){
        if(g.addNum(9)) {
            board.invalidate();
        }
    }
}