package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaParser;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SignupPage extends AppCompatActivity {

    private FirebaseAuth auth;

    public String userType;

    TextView usernameTxt;
    EditText username, email, number, password, confirmPassword;
    Button signup, restBtn, ngoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        auth  = FirebaseAuth.getInstance();

        restBtn = findViewById(R.id.restBtn);
        ngoBtn = findViewById(R.id.ngoBtn);


        usernameTxt = findViewById(R.id.usertype);
        Intent i = getIntent();
        String usertype = i.getStringExtra("usertype");
        usernameTxt.setText(usertype);

        username = findViewById(R.id.nameEdt);
        email = findViewById(R.id.signupEmail);
        number = findViewById(R.id.signupPhone);
        password = findViewById(R.id.passwordEditText);
        confirmPassword = findViewById(R.id.signupConfirmPass);

        signup = findViewById(R.id.signup);
        signup.setOnClickListener(v -> {
            if(username!=null && email!=null && number!=null && password!=null && confirmPassword!=null){
                if(password.equals(confirmPassword)){
                    Intent intent = new Intent(SignupPage.this,CardList.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Password and Confirm Passwords don't match !", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "No fields should be left blank", Toast.LENGTH_SHORT).show();
            }

        });



        restBtn.setOnClickListener(view -> {
            userType = "Restaurant";
            Toast.makeText(SignupPage.this, "userType is restaurant", Toast.LENGTH_SHORT).show();
        });

        ngoBtn.setOnClickListener(view -> {
            userType = "NGO";
            Toast.makeText(SignupPage.this, "userType is ngo", Toast.LENGTH_SHORT).show();
        });
    }
}