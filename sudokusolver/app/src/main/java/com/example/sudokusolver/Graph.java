package com.example.sudokusolver;

import java.util.*;
import java.util.HashMap;

public class Graph {

    private int row;
    private int col;

    List<Map<Integer, Integer>> map;
    int[][] board;
    ArrayList<Integer>[] rows;
    ArrayList<Integer>[] cols;
    ArrayList<Integer>[] blocks;
    ArrayList<Integer>[] neighbours;

    Graph(){
        row = 0;
        col = 0;
        rows = new ArrayList[9];
        cols = new ArrayList[9];
        blocks = new ArrayList[9];
        neighbours = new ArrayList[81];
        board = new int[9][9];
        map = new ArrayList<>();


        for(int i=0; i<81;i++) {
            map.add(new HashMap<>());
            map.get(i).put(i,0);
        }

        for(int row=0; row<9; row++){
            for(int col=0; col<9; col++){
                board[row][col] = 0;

            }
        }



        this.setRowEdges();
        this.setColEdges();
        this.setBlockEdges();
        this.setNeighbours();

    }

    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            System.out.print("\n");
            if(i%3==0)
                System.out.print("\n");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0)
                    System.out.print(" ");
                System.out.print(board[i][j]);
            }
        }
    }

    public void clearBoard(){
        for(int i=0; i<81;i++) {
            map.add(new HashMap<>());
            map.get(i).put(i,0);
        }

        for(int row=0; row<9; row++){
            for(int col=0; col<9; col++){
                board[row][col] = 0;

            }
        }
    }

    public ArrayList<Integer> availableColours(int idx, int colours) {
        ArrayList<Integer> unusedColours = new ArrayList<>();

        for(int colour=1;colour<colours;colour++) {
            unusedColours.add(colour);
        }

        for(int i:neighbours[idx]) {
            if(map.get(i).get(i)!=0) {
                unusedColours.remove(map.get(i).get(i));
            }
        }
        return unusedColours;
    }

    public boolean solver() {
        int idx = 0;
        for(int i=idx; i<81; i++) {
            if(map.get(i).get(i)==0) {
                idx = i;
                break;
            }else if(i==80) {
                return true;
            }
        }

        for(int colour:this.availableColours(idx,10)) {
            map.get(idx).replace(idx, colour);
            if(solver()) {
                return true;
            }
            map.get(idx).replace(idx, 0);
        }

        return false;

    }

    public void setSolveBoard() {
        int count = 0;
        for(int row=0;row<9;row++) {
            for(int col=0; col<9; col++) {
                board[row][col] = map.get(count).get(count);
                count++;
            }
        }
    }

    public boolean isCheck(int num, int idx) {
        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i : neighbours[idx]) {
            numbers.add(map.get(i).get(i));
        }
        // System.out.println(numbers);
        if (!numbers.contains(num)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addNum(int num){
        int idx = rows[row -1].get(col -1);
        if(num==0){
            this.board[row -1][col -1] = num;
            map.get(idx).replace(idx, num);
        }
        if(this.isCheck(num, idx)){
            this.board[row -1][col -1] = num;
            map.get(idx).replace(idx, num);
            return true;
        }else{
            return false;
        }
    }


    private void setNeighbours() {
        for(int i=0; i<81;i++) {
            neighbours[i] = new ArrayList<Integer>();
            for(int j=0; j<9;j++) {
                if(rows[j].contains(i)) {
                    neighbours[i].addAll(rows[j]);
                }
                if(cols[j].contains(i)) {
                    neighbours[i].addAll(cols[j]);
                }
                if(blocks[j].contains(i)) {
                    neighbours[i].addAll(blocks[j]);
                }
            }
        }

        for(int i=0; i<81; i++) {
            Set<Integer> set = new HashSet<>(neighbours[i]);
            neighbours[i].clear();
            neighbours[i].addAll(set);
            neighbours[i].remove(neighbours[i].indexOf(i));
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

    public int getRow(){
        return row;
    }

    public void setRow(int row){
        this.row = row;
    }

    public int getCol(){
        return col;
    }
    public void setCol(int col){
        this.col = col;
    }
}
