package com.example.sudokuapp;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailEt,passwordEt1,passwordEt2;
    private Button signUpButton;
    private TextView signInTv;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        firebaseAuth = firebaseAuth.getInstance();
        emailEt = findViewById(R.id.email);
        passwordEt1 = findViewById(R.id.password1);
        passwordEt2 = findViewById(R.id.password2);
        signUpButton = findViewById(R.id.register);
        progressDialog = new ProgressDialog(this);
        signInTv = findViewById(R.id.signInTv);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();

            }
        });
        signInTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
    private void Register(){
        String email = emailEt.getText().toString();
        String password1 = passwordEt1.getText().toString();
        String password2 = passwordEt2.getText().toString();

        if(TextUtils.isEmpty(email)){
            emailEt.setError("Enter your email ");
            return;
        }
        else if(TextUtils.isEmpty(password1)){
            passwordEt1.setError("Enter your password ");
            return;
        }
        else if(TextUtils.isEmpty(password2)){
            passwordEt2.setError("Confirm your password ");
            return;
        }
        else if(!password1.equals(password2)){
            passwordEt2.setError("Different password ");
            return;
        }
        else if(password1.length() < 6){
            passwordEt2.setError("the password must to be bigger 6 ");
            return;
        }
        else if(!isVallidEmail(email)){
            emailEt.setError("Invalid Email ");
            return;
        }
        progressDialog.setMessage("please wait ...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignUpActivity.this,"Successfully registred",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUpActivity.this,BoardGameractivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(SignUpActivity.this,"Sing Up Faild",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });

    }
    private boolean isVallidEmail(CharSequence target){
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
