package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class AllUserActivity extends AppCompatActivity {


    UserOpenHelper uoh;
    ArrayList<User> listOfUsers;
    ListView listView;
    UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user);


        listView = (ListView) findViewById(R.id.listview);
        uoh = new UserOpenHelper(this);

        listOfUsers = new ArrayList<User>();
        uoh.open();
        listOfUsers = uoh.getAllUsers();
        uoh.close();

        userAdapter = new UserAdapter(this, 0, listOfUsers);
        listView.setAdapter(userAdapter);

    }



}