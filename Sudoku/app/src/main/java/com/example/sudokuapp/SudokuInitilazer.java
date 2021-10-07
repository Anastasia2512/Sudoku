package com.example.sudokuapp;

import android.app.ProgressDialog;

import java.util.ArrayList;
import java.util.Random;

public class SudokuInitilazer {


    public static int[][] board;
    public static int selected_row;
    public static  int selected_column;
        SudokuInitilazer(){
            selected_row = -1;
            selected_column = -1;

            board = new int[9][9];

        }

        public boolean check(int row, int col){
            if(board[row][col] > 0){
                for (int i = 0; i < 9; i++){
                    if(board[i][col] == board[row][col] && row != i){
                        return false;
                    }
                    if(board[row][i] == board[row][col] && col != i){
                        return false;
                    }
                    int boxRow = row/3;
                    int boxCol = col/3;

                    for (int r = boxRow*3; r < boxRow*3+3;r++){
                        for(int c = boxCol*3; c< boxCol*3+3; c++){
                            if(board[r][c] == board[row][col] && row != r && col != c){
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }


        public boolean solve(SudokuBoard display){
            int row = -1;
            int col = -1;

            for (int r = 0; r < 9; r++){
                for (int c = 0; c < 9; c++){
                    if(board[r][c] == 0){
                        row = r;
                        col = c;
                    }
                }
            }
            if(row == -1 || col == -1){
                return true;
            }

            for (int i = 1 ; i <10 ; i++){
                board[row][col] = i;
                display.invalidate();

                if(check(row,col)){
                    if(solve(display)){
                        return true;
                    }
                }

                board[row][col] = 0;
            }
            return false;
        }

        public void resetGame(int num){
            Random random1 = new Random();
            Random random2 = new Random();
            for (int i = 0; i < num ; i++){
                int row = random1.nextInt(9);
                int col = random2.nextInt(9);
                if(board[row][col] != 0){
                    board[row][col] = 0;
                }else if(row+1 < board.length && board[row+1][col] != 0){
                    board[row+1][col] = 0;
                }else if(col+1 < board.length && board[row][col+1] != 0){
                    board[row][col+1] = 0;
                }else i--;

            }

        }

        public void setNumberPos(int num){
            if(selected_row!= -1 && selected_column != -1 ){
                board[selected_row-1][selected_column-1] = num;
            }

        }


        public static int[][] getBoard(){
            return board;
        }

        public int getSelected_row() {
            return selected_row;
        }

        public void setSelected_row(int row) {
            selected_row = row;
        }

        public int getSelected_column() {
            return selected_column;
        }

        public void setSelected_column(int column) {
            selected_column = column;
        }

    }
