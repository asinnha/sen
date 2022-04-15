package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class CardList extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);

        listView = findViewById(R.id.userList);

        String[] userNames = new String[] {
                "Delhi Zaika", "Hoco Eatery", "Swagat" , "Premvati"
        };

        ArrayList<String> userList =  new ArrayList<String>();
        userList.addAll(Arrays.asList(userNames));

        arrayAdapter = new ArrayAdapter<String>(CardList.this,R.layout.user_row,userList);
        listView.setAdapter(arrayAdapter);

    }
}