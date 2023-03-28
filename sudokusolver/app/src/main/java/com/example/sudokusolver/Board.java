package com.example.sudokusolver;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class Board extends View {
    private int lineColour;
    private Paint linePaint = new Paint();
    private Paint cellColourPaint = new Paint();
    private Paint numPaint = new Paint();
    private Rect numBounds = new Rect();
    private int cellSize;
    private int cellColour;
    private Graph graph = new Graph();

    public Board(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray arr = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Board,
                0,0);

        try{
            lineColour = arr.getInteger(R.styleable.Board_lineColour, 0);
            cellColour= arr.getInteger(R.styleable.Board_cellColour,0 );
        }finally {
            arr.recycle();
        }
    }

    @Override
    public void onMeasure(int width, int height){
        super.onMeasure(width, height);
        int dim = Math.min(this.getMeasuredWidth(), this.getMeasuredHeight());

        cellSize = dim/9;
        setMeasuredDimension(dim,dim);
    }

    @Override
    public void onDraw(Canvas c){

        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(lineColour);
        linePaint.setStrokeWidth(10);

        cellColourPaint.setStyle(Paint.Style.FILL);
        cellColourPaint.setColor(cellColour);

        numPaint.setStyle(Paint.Style.FILL);
        //numPaint.setColor();
        colourCell(c, graph.getSelectRow(), graph.getSelectCol());
        c.drawRect(0,0,getWidth(),getHeight(),linePaint);
        drawBoard(c);
        addNum(c);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        boolean touch;
        int a = event.getAction();

        if(a == MotionEvent.ACTION_DOWN){
            graph.setSelectCol((int)Math.ceil(event.getX()/cellSize));
            graph.setSelectRow((int)Math.ceil(event.getY()/cellSize));
            touch = true;
        }else{
            touch = false;
        }

        return touch;
    }


    private void setNum(Canvas c, int row, int col){
        if (graph.getBoard()[row][col] != 0) {
            String num = Integer.toString(graph.getBoard()[row][col]);

            numPaint.getTextBounds(num,0,num.length(), numBounds);

            c.drawText(num,col*cellSize,row*cellSize+cellSize, numPaint);
        }
    }
    private void addNum(Canvas c){
        numPaint.setTextSize(cellSize);
        for(int row=0; row<9; row++){
            for(int col=0; col<9; col++){
                this.setNum(c, row, col);
            }
        }
    }

    private void colourCell(Canvas c, int row, int col){
        if(graph.getSelectCol() != 0 && graph.getSelectRow() != 0){
            c.drawRect((col-1)*cellSize,(row-1)*cellSize, col*cellSize,
                    row*cellSize, cellColourPaint);
        }
        invalidate();
    }
    private void thickerLine(){
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(lineColour);
        linePaint.setStrokeWidth(12);
    }

    private void Line(){
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(lineColour);
        linePaint.setStrokeWidth(4);
    }

    private void drawBoard(Canvas c){
        for(int col=0; col<10; col++){
            if(col%3 == 0){
                thickerLine();
            }else{
                Line();
            }
            c.drawLine(cellSize * col, 0, cellSize*col ,
                    getWidth(), linePaint);
        }

        for(int row=0; row<10; row++){
            if(row%3 ==0){
                thickerLine();
            }else{
                Line();
            }
            c.drawLine(0, cellSize * row, getWidth() ,
                    cellSize * row, linePaint);
        }
    }

    public Graph getGraph(){
        return this.graph;
    }
}
