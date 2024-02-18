package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fitnessapp.popularWorkout.PopularWorkoutActivity;

public class WorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
    }

    public void move_to_popular_workouts_activity(View v) {
        startActivity(new Intent(WorkoutActivity.this, PopularWorkoutActivity.class));
    }
}