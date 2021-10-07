import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;


public class Sudoku {
    public static int[][] board;


    public Sudoku(int[][] temp) {
        board = temp;
    }



    public boolean checkNumber(int selected_row , int selected_column, int num){
        for (int i = 0; i < 9; i++){
            if(board[i][selected_column] == num && selected_row != i){
                return false;
            }
            if(board[selected_row][i] == num && selected_column != i){
                return false;
            }
            int boxRow = selected_row/3;
            int boxCol = selected_column/3;

            for (int r = boxRow*3; r < boxRow*3+3;r++){
                for(int c = boxCol*3; c< boxCol*3+3; c++){
                    if(board[r][c] == num && selected_row != r && selected_column != c){
                        return false;
                    }
                }
            }
        }
        return true;
    }



}






