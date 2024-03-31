package com.example.fitnessapp.PrivateWorkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fitnessapp.R;

public class WorkoutsScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts_screen);
    }

    public void move_to_create_workout_activity(View v) {
    startActivity(new Intent(WorkoutsScreen.this, WholeWorkout.class));
    }
}