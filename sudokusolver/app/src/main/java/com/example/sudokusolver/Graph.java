package com.example.sudokusolver;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    HashMap<Integer, Object> map;
    private int nodeId;

    private int selectRow;
    private int selectCol;
    int[][] board;

    ArrayList<Integer>[] rows;
    ArrayList<Integer>[] cols;
    ArrayList<Integer>[] blocks;

    Graph(){
        selectRow = 0;
        selectCol = 0;
        nodeId = 1;
        rows = new ArrayList[9];
        cols = new ArrayList[9];
        blocks = new ArrayList[9];
        board = new int[9][9];
        map = new HashMap<>();

        for(int row=0; row<9; row++){
            for(int col=0; col<9; col++){
                board[row][col] = 0;
                map.put(nodeId,board[row][col]);
                nodeId++;
            }
        }

        this.setRowEdges();
        this.setColEdges();
        this.setBlockEdges();

    }


    public void addNum(int num){
        if(this.selectRow != 0 && this.selectCol != 0){
            this.board[this.selectRow-1][this.selectCol-1] = num;

        }
    }

    private void setRowEdges(){
        //create edges for each row
        int counter = 0;
        for(int row=0; row<9;row++) {
            rows[row] =  new ArrayList<Integer>();
            for(int idx=0; idx<9; idx++) {
                rows[row].add(counter);
                counter = counter +1;
            }
        }
    }

    private void setColEdges(){
        //create edges for each column
        for(int col=0; col<9; col++){
            cols[col] = new ArrayList<Integer>();
            for(int idx=col; idx<81; idx=idx+9) {
                cols[col].add(idx);
            }
        }
    }

    private void setBlockEdges(){
        //create edges for each box
        int c = 0;
        int temp =0;
        for(int k=0; k<9; k++) {
            if((k+1)%3 ==0) {
                temp=temp+18;
            }
            blocks[k] = new ArrayList();
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    blocks[k].add(j+c);

                }
                c=c+9;
            }
            c=3*(k+1)+temp;
        }
    }



    public int[][] getBoard(){
        return this.board;
    }

    public int getSelectRow(){
        return selectRow;
    }

    public void setSelectRow(int row){
        selectRow = row;
    }

    public int getSelectCol(){
        return selectCol;
    }
    public void setSelectCol(int col){
        selectCol = col;
    }
}
