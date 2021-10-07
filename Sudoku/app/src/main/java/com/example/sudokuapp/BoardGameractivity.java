package com.example.sudokuapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class BoardGameractivity extends Activity {
    public static final String MSG_KEY = "com.example.sudokuapp.BoardGameractivity.extra";
    public static int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bordgamer);
    }


    public void easyClick(View view) {
         num = 33;
        Intent intent = new Intent(this,SudokuActivity.class);
        intent.putExtra(MSG_KEY, num);
        startActivity(intent);
    }

    public void mediomClick(View view) {
        num = 41;
        Intent intent = new Intent(this,SudokuActivity.class);
        intent.putExtra(MSG_KEY, num);
        startActivity(intent);
    }

    public void hardClick(View view) {
        num = 56;
        Intent intent = new Intent(this,SudokuActivity.class);
        intent.putExtra(MSG_KEY, num);
        startActivity(intent);
    }
}
