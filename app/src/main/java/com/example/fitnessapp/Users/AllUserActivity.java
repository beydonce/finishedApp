package com.example.fitnessapp.Users;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.Users.User;
import com.example.fitnessapp.Users.UserAdapter;
import com.example.fitnessapp.Users.UserOpenHelper;

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