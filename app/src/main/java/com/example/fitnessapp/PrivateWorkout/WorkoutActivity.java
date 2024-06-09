package com.example.fitnessapp.PrivateWorkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fitnessapp.R;
import com.example.fitnessapp.popularWorkout.PopularWorkoutActivity;

public class WorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
    }


    public void move_to_Workouts_screen_activity(View v) {
        startActivity(new Intent(WorkoutActivity.this, WorkoutsScreen.class));
    }
    public void move_to_popular_workouts_activity(View v) {
        startActivity(new Intent(this,PopularWorkoutActivity.class));
    }


}