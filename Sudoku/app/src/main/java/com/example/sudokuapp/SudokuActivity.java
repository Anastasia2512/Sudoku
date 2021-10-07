package com.example.sudokuapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class SudokuActivity extends AppCompatActivity {
    private SudokuBoard gameBoard;
    private SudokuInitilazer gameBoardNumber;
    static int diffclty;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(3);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);
        Intent intent = getIntent();
        diffclty = intent.getIntExtra(BoardGameractivity.MSG_KEY, BoardGameractivity.num);
        gameBoard = findViewById(R.id.SudokuBoard);
        gameBoardNumber = gameBoard.getNumbers();
        gameBoardNumber.solve(gameBoard);
        gameBoardNumber.resetGame(diffclty);
        gameBoard.invalidate();

    }


    public void BTNOnePress(View view)  {
        int one = 1;
        SudokuClient client = new SudokuClient(one);
        Future<Boolean> futureRes = threadPool.submit(client.action);
        try{
            boolean res = futureRes.get();
            if(res) {
                gameBoardNumber.setNumberPos(one);
                gameBoard.invalidate();
            }
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }

    }

    public void BTNTwoPress(View view)  {
        int two = 2;
        SudokuClient client = new SudokuClient(two);
        Future<Boolean> futureRes = threadPool.submit(client.action);
        try{
            boolean res = futureRes.get();
            if(res) {
                gameBoardNumber.setNumberPos(two);
                gameBoard.invalidate();
            }

        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }

    public void BTNThreePress(View view) {
        int three= 3;
        SudokuClient client = new SudokuClient(three);
        Future<Boolean> futureRes = threadPool.submit(client.action);
        try{
            boolean res = futureRes.get();
            if(res) {
                gameBoardNumber.setNumberPos(three);
                gameBoard.invalidate();
            }

        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }

    public void BTNFourPress(View view) {
        int four = 4;
        SudokuClient client = new SudokuClient(four);
        Future<Boolean> futureRes = threadPool.submit(client.action);
        try{
            boolean res = futureRes.get();
            if(res) {
                gameBoardNumber.setNumberPos(four);
                gameBoard.invalidate();
            }

        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }

    }

    public void BTNFivePress(View view) {
        int five = 5;
        SudokuClient client = new SudokuClient(five);
        Future<Boolean> futureRes = threadPool.submit(client.action);
        try{
            boolean res = futureRes.get();
            if(res) {
                gameBoardNumber.setNumberPos(five);
                gameBoard.invalidate();
            }

        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }

    }

    public void BTNSixPress(View view) {
        int six = 6;
        SudokuClient client = new SudokuClient(six);
        Future<Boolean> futureRes = threadPool.submit(client.action);
        try{
            boolean res = futureRes.get();
            if(res) {
                gameBoardNumber.setNumberPos(six);
                gameBoard.invalidate();
            }

        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }

    }

    public void BTNSevenPress(View view) {
        int seven = 7 ;
        SudokuClient client = new SudokuClient(seven);
        Future<Boolean> futureRes = threadPool.submit(client.action);
        try{
            boolean res = futureRes.get();
            if(res) {
                gameBoardNumber.setNumberPos(seven);
                gameBoard.invalidate();
            }

        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }

    public void BTNEightPress(View view) {
        int eight = 8;
        SudokuClient client = new SudokuClient(eight);
        Future<Boolean> futureRes = threadPool.submit(client.action);
        try{
            boolean res = futureRes.get();
            if(res) {
                gameBoardNumber.setNumberPos(eight);
                gameBoard.invalidate();
            }
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }

    public void BTNNinePress(View view) {
        int nine = 9;
        SudokuClient client = new SudokuClient(nine);
        Future<Boolean> futureRes = threadPool.submit(client.action);
        try{
            boolean res = futureRes.get();
            if(res) {
                gameBoardNumber.setNumberPos(nine);
                gameBoard.invalidate();
            }
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }

    public void clean(View view) {
        gameBoardNumber.setNumberPos(0);
        gameBoard.invalidate();
    }

}
