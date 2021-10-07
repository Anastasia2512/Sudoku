package com.example.sudokuapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText emailEt,passwordEt;
    private Button signInButton;
    private TextView signUpTv;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = firebaseAuth.getInstance();
        emailEt = findViewById(R.id.email);
        passwordEt = findViewById(R.id.password);

        signInButton = findViewById(R.id.login);
        progressDialog = new ProgressDialog(this);
        signUpTv = findViewById(R.id.signUpTv);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();

            }
        });
        signUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
    private void Register(){
        String email = emailEt.getText().toString();
        String password = passwordEt.getText().toString();


        if(TextUtils.isEmpty(email)){
            emailEt.setError("Enter your email ");
            return;
        }
        else if(TextUtils.isEmpty(password)){
            passwordEt.setError("Enter your password ");
            return;
        }

        progressDialog.setMessage("please wait ...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Successfully registred",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,BoardGameractivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(MainActivity.this,"Sing In Faild",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });

    }

}