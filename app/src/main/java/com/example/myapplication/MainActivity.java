package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText usernameEdt, passwordEdt;
    Button loginBtn, signupBtn;
    String username, password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        usernameEdt = findViewById(R.id.usernameEditTxt);
        passwordEdt = findViewById(R.id.passwordEditText);
        loginBtn = findViewById(R.id.loginBtn);
        signupBtn = findViewById(R.id.signupBtn);

        loginBtn.setOnClickListener(view -> {
            login();
        });
        try{
        signupBtn.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,SignupPage.class);
            startActivity(i);

        });}catch(Exception e){
            Log.e("error",e.getMessage());
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent i = new Intent(MainActivity.this,CardList.class);
            i.putExtra("username",currentUser);
            startActivity(i);

        }
    }

    private void login() {

        username = usernameEdt.getText().toString().trim();
        password = passwordEdt.getText().toString();

        if(username==null&&password==null){
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }else{
            mAuth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Log.d("done", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(this, "current user - "+user, Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MainActivity.this,CardList.class);
                            assert user != null;
                            i.putExtra("username",user.getEmail());
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            i.addFlags((Intent.FLAG_ACTIVITY_NEW_TASK));
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("error", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                     });
        }
    }
}