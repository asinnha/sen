package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaParser;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupPage extends AppCompatActivity {

    private FirebaseAuth auth;
    FirebaseFirestore db;

    TextView orgTypeEdt;
    EditText  emailEdt, numberEdt, passwordEdt, confirmPasswordEdt, organisationEdt;
    Button signupBtn, restBtn, ngoBtn;
    String orgType, email ,pass, confirmPass, orgName,phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        FirebaseApp.initializeApp(this);

        auth  = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        restBtn = findViewById(R.id.restBtn);
        ngoBtn = findViewById(R.id.ngoBtn);

        orgTypeEdt = findViewById(R.id.orgTypeEdt);
        organisationEdt =findViewById(R.id.orgNameEdt);
        numberEdt = findViewById(R.id.signupPhone);
        emailEdt = findViewById(R.id.signupEmail);
        passwordEdt= findViewById(R.id.signupPass);
        confirmPasswordEdt = findViewById(R.id.signupConfirmPass);

        signupBtn = findViewById(R.id.signup);
        signupBtn.setOnClickListener(v -> {
            signUp();
        });

        restBtn.setOnClickListener(view -> {
            orgTypeEdt.setText("Restuarant");
            //Toast.makeText(SignupPage.this, "userType is restaurant", Toast.LENGTH_SHORT).show();
            restBtn.setBackgroundColor(0xFF6200EE);
            ngoBtn.setBackgroundColor(0x5EACEA);
        });

        ngoBtn.setOnClickListener(view -> {
            orgTypeEdt.setText("NGO");
            //Toast.makeText(SignupPage.this, "userType is ngo", Toast.LENGTH_SHORT).show();
            ngoBtn.setBackgroundColor(0xFF6200EE);
            restBtn.setBackgroundColor(0x5EACEA);
        });
    }

    private void signUp() {
        orgType = orgTypeEdt.getText().toString().trim();
        orgName = organisationEdt.getText().toString().trim();
        email = emailEdt.getText().toString().trim();
        phoneNum = numberEdt.getText().toString().trim();
        pass = passwordEdt.getText().toString();
        confirmPass = confirmPasswordEdt.getText().toString();
        
        if(orgType==null && email==null && phoneNum==null && pass==null && confirmPass==null){
            Toast.makeText(this, "No fields should be left blank", Toast.LENGTH_SHORT).show();
        }else{
            if(pass.equals(confirmPass)){

                Toast.makeText(this, "orgType :"+ orgType+"\nemail"+email+"\nnum-"+phoneNum+"\npass-"+pass+"\ncpass"+confirmPass, Toast.LENGTH_LONG).show();
                auth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                Log.d("debug", "createUserWithEmail:success");
                                insertData();
                                FirebaseUser user = auth.getCurrentUser();
                                Intent intent = new Intent(SignupPage.this,CardList.class);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("error", task.getException());
                                Toast.makeText(SignupPage.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }else{
                Toast.makeText(this, "Password and Confirm Passwords don't match !", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void insertData() {
        orgType = orgTypeEdt.getText().toString().trim();
        orgName = organisationEdt.getText().toString().trim();
        email = emailEdt.getText().toString().trim();
        phoneNum = numberEdt.getText().toString().trim();
        pass = passwordEdt.getText().toString();
        confirmPass = confirmPasswordEdt.getText().toString();

        Map<String, Object> user = new HashMap<>();
        user.put("organisation type", orgType);
        user.put("organisation name", orgName);
        user.put("email", email);
        user.put("contact number", phoneNum);
        user.put("password", pass);

        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("done", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("error", "Error adding document", e);
                    }
                });

    }



}