package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText usernameEdt, passwordEdt;
    Button loginBtn, signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEdt = findViewById(R.id.usernameEditTxt);
        passwordEdt = findViewById(R.id.passwordEditText);
        loginBtn = findViewById(R.id.loginBtn);
        signupBtn = findViewById(R.id.signupBtn);

        loginBtn.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,CardList.class);
            startActivity(i);
        });

        signupBtn.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,SignupPage.class);
            startActivity(i);
        });


    }

}