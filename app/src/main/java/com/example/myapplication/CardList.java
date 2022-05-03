package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;

public class CardList extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    Button nextBtn,signoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);

        listView = findViewById(R.id.userList);
        nextBtn = findViewById(R.id.nextBtn);

        Intent i = getIntent();
        String username = i.getStringExtra("username");
        Toast.makeText(this, "Welcome "+username, Toast.LENGTH_LONG).show();

        String[] userNames = new String[] {
                "Delhi Zaika", "Hoco Eatery", "Swagat" , "Premvati"
        };

        ArrayList<String> userList =  new ArrayList<String>();
        userList.addAll(Arrays.asList(userNames));

        arrayAdapter = new ArrayAdapter<String>(CardList.this,R.layout.user_row,userList);
        listView.setAdapter(arrayAdapter);


        nextBtn.setOnClickListener(view -> {
                Intent intent = new Intent(CardList.this,BookingPage.class);
                startActivity(intent);
        });

        signoutBtn= findViewById(R.id.signout);
        signoutBtn.setOnClickListener(v ->{
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(CardList.this,MainActivity.class);
            startActivity(intent);
        });
    }
}