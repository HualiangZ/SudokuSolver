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

    public void btnOne(View view){
        g.addNum(1);
        board.invalidate();
    }

    public void btnTwo(View view){
        g.addNum(2);
        board.invalidate();
    }

    public void btnThree(View view){
        g.addNum(3);
        board.invalidate();
    }

    public void btnFour(View view){
        g.addNum(4);
        board.invalidate();
    }

    public void btnFive(View view){
        g.addNum(5);
        board.invalidate();
    }

    public void btnSix(View view){
        g.addNum(6);
        board.invalidate();
    }

    public void btnSeven(View view){
        g.addNum(7);
        board.invalidate();
    }

    public void btnEight(View view){
        g.addNum(8);
        board.invalidate();
    }

    public void btnNine(View view){
        g.addNum(9);
        board.invalidate();
    }
}