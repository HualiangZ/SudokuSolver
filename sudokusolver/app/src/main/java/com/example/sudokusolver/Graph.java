package com.example.sudokusolver;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
//    HashMap<Integer, Object> map;
//    private int nodeId;

    private int selectRow;
    private int selectCol;
    int[][] board;

//    ArrayList<Integer> neighbour[];

    Graph(){
        selectRow = 0;
        selectCol = 0;
//        nodeId = 1;
        board = new int[9][9];


//        neighbour = new ArrayList[81];
//        for (int i=1; i<82; ++i)
//            neighbour[i] = new ArrayList();
//
//        map = new HashMap<>();
//
//        for(int row=0; row<9; row++){
//            for(int col=0; col<9; col++){
//                board[row][col] = 0;
//                map.put(nodeId,board[row][col]);
//                nodeId++;
//            }
//        }



    }

//    public void addEdges(int num, int nextNum){
//        neighbour[num].add(nextNum);
//        neighbour[nextNum].add(num);
//    }
//
//    public void edges(){
//        int row = 1;
//        //add edges for each row
//        for(int idx=1; idx<82; idx++){
//            if(idx != row){
//                this.addEdges(row,idx);
//            }
//            if(idx%9 == 0){
//                row=row+9;
//            }
//        }
//
//        //add edges for each column
//        for(int col=1; col<10; col++){
//            for(int idx=col; idx<82; idx=idx+9){
//                if(idx != col){
//                    this.addEdges(col, idx);
//                }
//            }
//        }
//    }


    public void addNum(int num){
        if(this.selectRow != 0 && this.selectCol != 0){
            this.board[this.selectRow-1][this.selectCol-1] = num;

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
